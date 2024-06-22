package com.farm.pedia.board.dto.response;

import java.time.LocalDateTime;

import com.farm.pedia.board.domain.Board;

import lombok.Getter;

@Getter
public class BoardReadResponse {

	private Long boardId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private Long userId;

	private BoardReadResponse() {
	}

	public BoardReadResponse(final Long boardId, final String title, final String content,
		final LocalDateTime createdAt, final Long userId) {
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.userId = userId;
	}

	public static BoardReadResponse from(final Board board) {
		return new BoardReadResponse(board.getId(), board.getTitle(), board.getContent(),
			board.getCreatedAt(), board.getUserId());
	}
}