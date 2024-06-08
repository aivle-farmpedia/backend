package com.farm.pedia.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Board {

	private Long id;
	private String title;
	private String content;
	private Long userId;
	private Long categoryId;
	private int state;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private Board(Long id, String title, String content, Long userId, Long categoryId, int state,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.categoryId = categoryId;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static Board of(String title, String content, Long userId, Long categoryId) {
		return new Board(null, title, content, userId, categoryId, 0, LocalDateTime.now(), LocalDateTime.now());
	}

	public void update(String title, String content, Long categoryId) {
		this.title = title;
		this.content = content;
		this.categoryId = categoryId;
		this.updatedAt = LocalDateTime.now();
	}

	public void isAuthor(Long userId) {
		if (!this.userId.equals(userId)) {
			throw new IllegalArgumentException("게시글 작성자만 가능합니다.");
		}
	}

	public void isDeleted() {
		if (this.state == 1) {
			throw new IllegalArgumentException("이미 삭제된 게시글입니다.");
		}
	}
}