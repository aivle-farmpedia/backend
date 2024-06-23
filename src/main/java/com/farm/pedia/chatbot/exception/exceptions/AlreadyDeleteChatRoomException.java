package com.farm.pedia.chatbot.exception.exceptions;

public class AlreadyDeleteChatRoomException extends RuntimeException {

	public AlreadyDeleteChatRoomException() {
		super("이미 삭제된 채팅방입니다.");
	}
}
