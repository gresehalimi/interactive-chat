package com.assignment.interactivechat.mapper;

import com.assignment.interactivechat.dto.MessageData;
import com.assignment.interactivechat.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MessageMapper.class)
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "user.id",source = "userId")
    Message messageDataToMessage(MessageData messageData);

    @Mapping(target = "userId",source = "user.id")
    MessageData messageToMessageData(Message message);
}
