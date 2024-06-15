package com.farm.pedia.comment.exception.exceptions;

public class CommentNotFoundException extends RuntimeException {

	public CommentNotFoundException() {
		super("해당 댓글을 찾을 수 없습니다.");
	}
}
