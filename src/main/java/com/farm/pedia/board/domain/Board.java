package com.farm.pedia.board.domain;

import java.time.LocalDateTime;

import com.farm.pedia.board.exception.exceptions.BoardAuthorOnlyException;
import com.farm.pedia.board.exception.exceptions.BoardAlreadyDeletedException;

import lombok.Getter;

@Getter
public class Board {

	private Long id;
	private String title;
	private String content;
	private Long userId;
	private int state;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private Board(Long id, String title, String content, Long userId, int state,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static Board of(String title, String content, Long userId) {
		return new Board(null, title, content, userId, 0, LocalDateTime.now(), LocalDateTime.now());
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
		this.updatedAt = LocalDateTime.now();
	}

	public void isAuthor(Long userId) {
		if (!this.userId.equals(userId)) {
			throw new BoardAuthorOnlyException();
		}
	}

	public void isDeleted() {
		if (this.state == 1) {
			throw new BoardAlreadyDeletedException();
		}
	}
}