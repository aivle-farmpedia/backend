package com.farm.pedia.chatbot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.farm.pedia.chatbot.exception.exceptions.AlreadyDeleteChatRoomException;
import com.farm.pedia.chatbot.exception.exceptions.ChatServerConnectionException;
import com.farm.pedia.chatbot.exception.exceptions.InvalidChatRoomException;
import com.farm.pedia.chatbot.exception.exceptions.InvalidRequestChatException;
import com.farm.pedia.global.exception.AbstractExceptionHandler;

@RestControllerAdvice
public class ChatBotExceptionHandler extends AbstractExceptionHandler {

	@ExceptionHandler(InvalidChatRoomException.class)
	public ResponseEntity<String> handleInvalidChatRoomException(InvalidChatRoomException e) {
		return handleBadRequest(e);
	}

	@ExceptionHandler(AlreadyDeleteChatRoomException.class)
	public ResponseEntity<String> handleAlreadyDeleteChatRoomException(AlreadyDeleteChatRoomException e) {
		return handleBadRequest(e);
	}

	@ExceptionHandler(InvalidRequestChatException.class)
	public ResponseEntity<String> handleInvalidRequestChatException(InvalidRequestChatException e) {
		return handleBadRequest(e);
	}

	@ExceptionHandler(ChatServerConnectionException.class)
	public ResponseEntity<String> handleChatServerConnectionException(ChatServerConnectionException e) {
		return handleInternalServerError(e);
	}
}
