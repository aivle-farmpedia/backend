package com.farm.pedia.chatbot.dto.response;

import lombok.Getter;

@Getter
public class ChatBotCreateResponse {

	private final Long id;

	private ChatBotCreateResponse(Long id) {
		this.id = id;
	}

	public static ChatBotCreateResponse of(Long id) {
		return new ChatBotCreateResponse(id);
	}
}
