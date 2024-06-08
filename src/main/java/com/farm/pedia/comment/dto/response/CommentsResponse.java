package com.farm.pedia.comment.dto.response;

import com.farm.pedia.comment.domain.Comment;
import com.github.pagehelper.Page;

import lombok.Getter;

@Getter
public class CommentsResponse {

	Page<Comment> comments;

	private CommentsResponse(Page<Comment> comments) {
		this.comments = comments;
	}

	public static CommentsResponse from(Page<Comment> comments) {
		return new CommentsResponse(comments);
	}
}
