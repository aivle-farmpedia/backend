package com.farm.pedia.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farm.pedia.auth.config.UserLogin;
import com.farm.pedia.board.domain.Board;
import com.farm.pedia.board.dto.request.BoardCreateRequest;
import com.farm.pedia.board.dto.request.BoardUpdateRequest;
import com.farm.pedia.board.dto.response.BoardReadResponse;
import com.farm.pedia.board.dto.response.BoardWriteResponse;
import com.farm.pedia.board.service.BoardService;
import com.farm.pedia.global.dto.response.PagedResponse;
import com.farm.pedia.user.domain.User;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BoardWriteResponse createBoard(@UserLogin User user, @RequestBody BoardCreateRequest boardCreateRequest) {
		Board board = boardService.createBoard(user, boardCreateRequest);

		return BoardWriteResponse.from(board);
	}

	@GetMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public BoardReadResponse findBoard(@PathVariable Long boardId) {
		Board board = boardService.findBoard(boardId);
		return BoardReadResponse.from(board);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PagedResponse<Board> findAll(
		@Min(value = 1, message = "페이지 값은 최소 1이어야 합니다.") @RequestParam(defaultValue = "1") int page,
		@Min(value = 1, message = "크기 값은 최소 1이어야 합니다.") @RequestParam(defaultValue = "10") int size) {
		return boardService.findAll(page, size);
	}

	@PutMapping("/{boardId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBoard(@UserLogin User user, @PathVariable Long boardId,
		@RequestBody BoardUpdateRequest boardUpdateRequest) {
		boardService.updateBoard(user, boardId, boardUpdateRequest);
	}

	@DeleteMapping("/{boardId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBoard(@UserLogin User user, @PathVariable Long boardId) {
		boardService.deleteBoard(user, boardId);
	}
}
