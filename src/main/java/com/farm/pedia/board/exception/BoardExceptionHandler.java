package com.farm.pedia.board.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.farm.pedia.board.exception.exceptions.BoardAlreadyDeletedException;
import com.farm.pedia.board.exception.exceptions.BoardAuthorOnlyException;
import com.farm.pedia.board.exception.exceptions.BoardNotFoundException;
import com.farm.pedia.global.exception.AbstractExceptionHandler;

@RestControllerAdvice
public class BoardExceptionHandler extends AbstractExceptionHandler {

	@ExceptionHandler(BoardNotFoundException.class)
	public ResponseEntity<String> handleBoardNotFoundException(BoardNotFoundException e) {
		return handleNotFound(e);
	}

	@ExceptionHandler(BoardAlreadyDeletedException.class)
	public ResponseEntity<String> handleBoardAlreadyDeletedException(BoardAlreadyDeletedException e) {
		return handleBadRequest(e);
	}

	@ExceptionHandler(BoardAuthorOnlyException.class)
	public ResponseEntity<String> handleBoardAuthorOnlyException(BoardAuthorOnlyException e) {
		return handleForbidden(e);
	}

}
