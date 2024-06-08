package com.farm.pedia.board.dto.response;

import java.time.LocalDateTime;

import com.farm.pedia.board.domain.Board;

import lombok.Getter;

@Getter
public class BoardWriteResponse {

	private final Long boardId;
	private final String title;
	private final String content;
	private final LocalDateTime createdAt;

	private BoardWriteResponse(Long boardId, String title, String content, LocalDateTime createdAt) {
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}

	public static BoardWriteResponse from(Board board) {
		return new BoardWriteResponse(board.getId(), board.getTitle(), board.getContent(), board.getCreatedAt());
	}
}
