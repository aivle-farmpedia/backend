package com.farm.pedia.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farm.pedia.board.domain.Board;
import com.farm.pedia.board.dto.request.BoardCreateRequest;
import com.farm.pedia.board.dto.request.BoardUpdateRequest;
import com.farm.pedia.board.exception.exceptions.BoardNotFoundException;
import com.farm.pedia.board.mapper.BoardMapper;
import com.farm.pedia.global.dto.response.PagedResponse;
import com.farm.pedia.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper boardMapper;

	public Board createBoard(User user, BoardCreateRequest boardCreateRequest) {
		Board board = Board.of(boardCreateRequest.title(), boardCreateRequest.content(),
			user.getId());
		boardMapper.save(board);

		return board;
	}

	public Board findBoard(Long boardId) {
		Board board = boardMapper.findById(boardId)
			.orElseThrow(BoardNotFoundException::new);
		board.isDeleted();
		return board;
	}

	public PagedResponse<Board> findAll(int page, int size) {
		int offset = (page - 1) * size;
		List<Board> boards = boardMapper.findAll(size, offset);
		int totalElements = boardMapper.countAll();
		int totalPages = (int) Math.ceil((double) totalElements / size);

		return PagedResponse.of(boards, page, size, totalElements, totalPages);
	}
	public void updateBoard(User user, Long boardId, BoardUpdateRequest boardUpdateRequest) {
		Board board = findBoard(boardId);

		board.isDeleted();
		board.isAuthor(user.getId());

		board.update(boardUpdateRequest.title(), boardUpdateRequest.content());

		boardMapper.update(board);
	}

	public void deleteBoard(User user, Long boardId) {
		Board board = findBoard(boardId);

		board.isDeleted();
		board.isAuthor(user.getId());

		boardMapper.logicalDelete(board.getId());
	}

}
