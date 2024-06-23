package com.farm.pedia.chatbot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.farm.pedia.chatbot.domain.Message;

@Mapper
public interface MessageMapper {

	void save(Message message);

	List<Message> findByChatroomId(Long chatRoomId);
}
