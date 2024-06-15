package com.farm.pedia.comment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.farm.pedia.comment.exception.exceptions.CommentAlreadyDeletedException;
import com.farm.pedia.comment.exception.exceptions.CommentAuthorOnlyException;
import com.farm.pedia.comment.exception.exceptions.CommentNotFoundException;
import com.farm.pedia.global.exception.AbstractExceptionHandler;

public class CommentExceptionHandler extends AbstractExceptionHandler {

	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<String>  handleCommentNotFoundException(CommentNotFoundException e) {
		return handleNotFound(e);
	}

	@ExceptionHandler(CommentAlreadyDeletedException.class)
	public ResponseEntity<String>  handleCommentAlreadyDeletedException(CommentAlreadyDeletedException e) {
		return handleBadRequest(e);
	}

	@ExceptionHandler(CommentAuthorOnlyException.class)
	public ResponseEntity<String>  handleCommentAuthorOnlyException(CommentAuthorOnlyException e) {
		return handleForbidden(e);
	}
}
