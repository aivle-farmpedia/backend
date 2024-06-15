package com.farm.pedia.comment.domain;

import java.time.LocalDateTime;

import com.farm.pedia.comment.exception.exceptions.CommentAlreadyDeletedException;
import com.farm.pedia.comment.exception.exceptions.CommentAuthorOnlyException;

import lombok.Getter;

@Getter
public class Comment {

	private final Long id;

	private final Long boardId;

	private final Long userId;

	private String content;

	private final int state;

	private final Long parentId;

	private final LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Comment(Long id, Long boardId, Long userId, String content, int state, Long parentId,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.id = id;
		this.boardId = boardId;
		this.userId = userId;
		this.content = content;
		this.state = state;
		this.parentId = parentId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static Comment parent(Long boardId, Long userId, String content) {
		return new Comment(null, boardId, userId, content, 0, null, LocalDateTime.now(), LocalDateTime.now());
	}

	public static Comment child(Long boardId, Long userId, String content, Long parentId) {
		return new Comment(null, boardId, userId, content, 0, parentId, LocalDateTime.now(), LocalDateTime.now());
	}

	public void update(String content) {
		this.content = content;
		this.updatedAt = LocalDateTime.now();
	}

	public void isAuthor(Long userId) {
		if (!this.userId.equals(userId)) {
			throw new CommentAuthorOnlyException();
		}
	}

	public void isDeleted() {
		if (this.state == 1) {
			throw new CommentAlreadyDeletedException();
		}
	}
}
