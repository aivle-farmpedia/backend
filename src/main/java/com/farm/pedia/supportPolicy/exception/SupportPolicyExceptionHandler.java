package com.farm.pedia.supportPolicy.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.farm.pedia.global.exception.AbstractExceptionHandler;
import com.farm.pedia.supportPolicy.exception.exceptions.SupportPolicyNotFoundException;

@RestControllerAdvice
public class SupportPolicyExceptionHandler extends AbstractExceptionHandler {

	@ExceptionHandler(SupportPolicyNotFoundException.class)
	public ResponseEntity<String> handleSupportPolicyNotFoundException(SupportPolicyNotFoundException e) {
		return handleNotFound(e);
	}
}
