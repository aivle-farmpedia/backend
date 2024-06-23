package com.farm.pedia.chatbot.exception.exceptions;

public class InvalidRequestChatException extends RuntimeException {

	public InvalidRequestChatException() {
		super("유효하지 않은 채팅 요청입니다.");
	}
}
