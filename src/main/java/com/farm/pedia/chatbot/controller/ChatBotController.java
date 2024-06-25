package com.farm.pedia.chatbot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.farm.pedia.auth.config.UserLogin;
import com.farm.pedia.chatbot.domain.ChatRoom;
import com.farm.pedia.chatbot.domain.Message;
import com.farm.pedia.chatbot.dto.request.ChatBotRequest;
import com.farm.pedia.chatbot.dto.response.ChatBotCreateResponse;
import com.farm.pedia.chatbot.service.ChatBotService;
import com.farm.pedia.user.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/chat-bot")
@RequiredArgsConstructor
@Slf4j
public class ChatBotController {

	private final ChatBotService chatBotService;

	@GetMapping("/chat-rooms")
	@ResponseStatus(HttpStatus.OK)
	public List<ChatRoom> getChatRooms(@UserLogin User user) {
		log.info("유저 {}의 채팅방 목록 조회", user.getId());
		return chatBotService.getChatRooms(user);
	}

	@GetMapping("/chat-rooms/{chatroomId}/messages")
	@ResponseStatus(HttpStatus.OK)
	public List<Message> getMessagesByChatRoom(@UserLogin User user, @PathVariable Long chatroomId) {
		log.info("유저 {}의 채팅방 {}의 메시지 목록 조회", user.getId(), chatroomId);
		return chatBotService.getMessagesByChatRoom(chatroomId, user);
	}

	@PostMapping("/chat-rooms")
	@ResponseStatus(HttpStatus.CREATED)
	public ChatBotCreateResponse createChatRoom(@UserLogin User user) {
		log.info("유저 {}의 채팅방 생성", user.getId());
		ChatRoom chatRoom = chatBotService.createChatRoom(user);

		return ChatBotCreateResponse.of(chatRoom.getId());
	}

	@PostMapping("/chat-rooms/{chatroomId}/messages")
	@ResponseStatus(HttpStatus.OK)
	public void sendMessage(@UserLogin User user, @PathVariable Long chatroomId,
		@RequestBody ChatBotRequest chatBotRequest) {
		log.info("유저 {}의 채팅방 {}에 메시지 전송", user.getId(), chatroomId);
		chatBotService.sendMessage(chatroomId, chatBotRequest, user);
	}

	@DeleteMapping("/chat-rooms/{chatroomId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteChatRoom(@PathVariable Long chatroomId, @UserLogin User user) {
		log.info("유저 {}의 채팅방 {} 삭제", user.getId(), chatroomId);
		chatBotService.deleteChatRoom(chatroomId, user);
	}
}
