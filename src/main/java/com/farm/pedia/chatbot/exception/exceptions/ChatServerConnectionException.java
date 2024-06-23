package com.farm.pedia.chatbot.exception.exceptions;

public class ChatServerConnectionException extends RuntimeException {

	public ChatServerConnectionException() {
		super("챗봇 서버와 연결할 수 없습니다.");
	}
}
