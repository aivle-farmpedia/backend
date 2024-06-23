package com.farm.pedia.chatbot.dto.response;

import java.util.List;

import lombok.Getter;

@Getter
public class ChatBotResponse {
	private final String status;
	private final String message;
	private final String response;
	private final List<Dou성ble> similarity_scores;

	private ChatBotResponse(String status, String message, String response, List<Double> similarity_scores) {
		this.status = status;
		this.message = message;
		this.response = response;
		this.similarity_scores = similarity_scores;
	}
}