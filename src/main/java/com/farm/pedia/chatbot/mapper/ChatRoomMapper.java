package com.farm.pedia.chatbot.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.farm.pedia.chatbot.domain.ChatRoom;

@Mapper
public interface ChatRoomMapper {

	void save(ChatRoom chatRoom);

	void logicalDelete(Long id);

	Optional<ChatRoom> findByIdAndUserId(Long id, Long userId);

	List<ChatRoom> findAllByUserIdAndIsDeletedFalse(Long userId);

	void updateRecentMsg(Long id, String recentMsg);

	ChatRoom findByUserIdAndRecentChatRoom(Long userId);
}
