package com.farm.pedia.chatbot.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Message {

	private final Long id;
	private final Long chatRoomId;
	private final String question;
	private final String answer;
	private final LocalDateTime createdAt;

	private Message(Long id, Long chatRoomId, String question, String answer, LocalDateTime createdAt) {
		this.id = id;
		this.chatRoomId = chatRoomId;
		this.question = question;
		this.answer = answer;
		this.createdAt = createdAt;
	}

	public static Message of(Long chatRoomId, String question, String answer) {
		return new Message(null, chatRoomId, question, answer, LocalDateTime.now());
	}
}
