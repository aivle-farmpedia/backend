package com.farm.pedia.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farm.pedia.board.domain.Board;
import com.farm.pedia.board.dto.request.BoardCreateRequest;
import com.farm.pedia.board.dto.request.BoardUpdateRequest;
import com.farm.pedia.board.mapper.BoardMapper;
import com.farm.pedia.user.domain.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper boardMapper;

	public Board createBoard(User user, BoardCreateRequest boardCreateRequest) {
		Board board = Board.of(boardCreateRequest.title(), boardCreateRequest.content(),
			user.getId(), boardCreateRequest.categoryId());
		boardMapper.save(board);

		return board;
	}

	public Board findBoard(Long boardId) {
		Board board = boardMapper.findById(boardId)
			.orElseThrow(() -> new RuntimeException("Board not found"));
		board.isDeleted();
		return board;
	}

	public Page<Board> findAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return boardMapper.findAll();
	}

	public void updateBoard(User user, Long boardId, BoardUpdateRequest boardUpdateRequest) {
		Board board = findBoard(boardId);

		board.isDeleted();
		board.isAuthor(user.getId());

		board.update(boardUpdateRequest.title(), boardUpdateRequest.content(), boardUpdateRequest.categoryId());

		boardMapper.update(board);
	}

	public void deleteBoard(User user, Long boardId) {
		Board board = findBoard(boardId);

		board.isDeleted();
		board.isAuthor(user.getId());

		boardMapper.logicalDelete(board.getId());
	}

}
