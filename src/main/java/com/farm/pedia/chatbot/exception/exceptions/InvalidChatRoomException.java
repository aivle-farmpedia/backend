package com.farm.pedia.chatbot.exception.exceptions;

public class InvalidChatRoomException extends RuntimeException {

	public InvalidChatRoomException() {
		super("유효하지 않은 채팅방입니다.");
	}
}
