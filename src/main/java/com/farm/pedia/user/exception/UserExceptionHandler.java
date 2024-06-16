package com.farm.pedia.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.farm.pedia.global.exception.AbstractExceptionHandler;
import com.farm.pedia.user.exception.exceptions.UserNotFoundException;

@RestControllerAdvice
public class UserExceptionHandler extends AbstractExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
		return handleNotFound(e);
	}
}
