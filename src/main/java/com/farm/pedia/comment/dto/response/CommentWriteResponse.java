package com.farm.pedia.comment.dto.response;

import java.time.LocalDateTime;

import com.farm.pedia.comment.domain.Comment;

import lombok.Getter;

@Getter
public class CommentWriteResponse {

	private final Long id;
	private final String content;
	private final LocalDateTime createdAt;

	private CommentWriteResponse(Long id, String content, LocalDateTime createdAt) {
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
	}

	public static CommentWriteResponse from(Comment comment) {
		return new CommentWriteResponse(comment.getId(), comment.getContent(), comment.getCreatedAt());
	}
}
