package com.farm.pedia.chatbot.service;

import com.farm.pedia.chatbot.domain.ChatRoom;
import com.farm.pedia.chatbot.domain.Message;
import com.farm.pedia.chatbot.dto.request.ChatBotRequest;
import com.farm.pedia.chatbot.dto.response.ChatBotResponse;
import com.farm.pedia.chatbot.exception.exceptions.ChatServerConnectionException;
import com.farm.pedia.chatbot.exception.exceptions.InvalidChatRoomException;
import com.farm.pedia.chatbot.mapper.ChatRoomMapper;
import com.farm.pedia.chatbot.mapper.MessageMapper;
import com.farm.pedia.user.domain.User;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatBotService {

	private final ChatRoomMapper chatRoomMapper;
	private final MessageMapper messageMapper;
	private final WebClient webClient;

	@Transactional(readOnly = true)
	public List<ChatRoom> getChatRooms(User user) {
		return chatRoomMapper.findAllByUserIdAndIsDeletedFalse(user.getId());
	}

	@Transactional(readOnly = true)
	public List<Message> getMessagesByChatRoom(Long chatroomId, User user) {
		validateChatRoomOwnership(chatroomId, user);
		return messageMapper.findByChatroomId(chatroomId);
	}

	public void createChatRoom(User user) {
		ChatRoom chatRoom = ChatRoom.of(user.getId());
		chatRoomMapper.save(chatRoom);
	}

	@Transactional
	public void sendMessage(Long chatroomId, ChatBotRequest chatBotRequest, User user) {
		validateChatRoomOwnership(chatroomId, user);

		String answer = sendQuestionToPythonServer(chatBotRequest);

		Message message = Message.of(chatroomId, chatBotRequest.message(), answer);
		messageMapper.save(message);

		chatRoomMapper.updateRecentMsg(chatroomId, chatBotRequest.message());
	}

	@Transactional
	public void deleteChatRoom(Long chatroomId, User user) {
		ChatRoom chatRoom = validateChatRoomOwnership(chatroomId, user);
		chatRoomMapper.logicalDelete(chatRoom.getId());
	}

	private ChatRoom validateChatRoomOwnership(Long chatroomId, User user) {
		return chatRoomMapper.findByIdAndUserId(chatroomId, user.getId())
			.orElseThrow(InvalidChatRoomException::new);
	}

	private String sendQuestionToPythonServer(ChatBotRequest chatBotRequest) {
		return webClient.post()
			.body(Mono.just(chatBotRequest), ChatBotRequest.class)
			.retrieve()
			.bodyToMono(ChatBotResponse.class)
			.doOnError(e -> {
				throw new ChatServerConnectionException();
			})
			.blockOptional()
			.map(ChatBotResponse::getResponse)
			.orElseThrow(ChatServerConnectionException::new);
	}
}