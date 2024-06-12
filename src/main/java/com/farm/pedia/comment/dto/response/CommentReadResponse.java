package com.farm.pedia.comment.dto.response;

import com.farm.pedia.comment.domain.Comment;

import lombok.Getter;

@Getter
public class CommentReadResponse {

	private final String content;

	private CommentReadResponse(String content) {
		this.content = content;
	}

	public static CommentReadResponse from(Comment comment) {
		return new CommentReadResponse(comment.getContent());
	}
}
