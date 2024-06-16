package com.farm.pedia.comment.exception.exceptions;

public class CommentAlreadyDeletedException extends RuntimeException {

	public CommentAlreadyDeletedException() {
		super("이미 삭제된 댓글입니다.");
	}
}
