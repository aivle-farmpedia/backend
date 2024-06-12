package com.farm.pedia.board.dto.response;

import com.farm.pedia.board.domain.Board;
import com.github.pagehelper.Page;

import lombok.Getter;

@Getter
public class BoardsResponse {

	Page<Board> boards;

	private BoardsResponse(Page<Board> boards) {
		this.boards = boards;
	}

	public static BoardsResponse from(Page<Board> boards) {
		return new BoardsResponse(boards);
	}
}
