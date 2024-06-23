package com.farm.pedia.chatbot.domain;

import java.time.LocalDateTime;

import com.farm.pedia.chatbot.exception.exceptions.AlreadyDeleteChatRoomException;

import lombok.Getter;

@Getter
public class ChatRoom {

	private final Long id;
	private final Long userId;
	private final String recentMsg;
	private final LocalDateTime createdAt;
	private final boolean isDeleted;

	private ChatRoom(Long id, Long userId, String recentMsg, LocalDateTime createdAt, boolean isDeleted) {
		this.id = id;
		this.userId = userId;
		this.recentMsg = recentMsg;
		this.createdAt = createdAt;
		this.isDeleted = isDeleted;
	}

	public static ChatRoom of(Long userId) {
		return new ChatRoom(null, userId, "새로운 대화", LocalDateTime.now(), false);
	}
}