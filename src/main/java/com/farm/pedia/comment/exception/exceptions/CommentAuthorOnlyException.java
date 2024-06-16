package com.farm.pedia.comment.exception.exceptions;

public class CommentAuthorOnlyException extends RuntimeException {

	public CommentAuthorOnlyException() {
		super("작성자만 수정, 삭제가 가능합니다.");
	}
}
