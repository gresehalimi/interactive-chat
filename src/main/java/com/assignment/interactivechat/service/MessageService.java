package com.assignment.interactivechat.service;

import com.assignment.interactivechat.constants.MessageStatus;
import com.assignment.interactivechat.dto.MessageData;
import com.assignment.interactivechat.exception.ApiException;
import com.assignment.interactivechat.exception.ResponseStatus;
import com.assignment.interactivechat.mapper.MessageMapper;
import com.assignment.interactivechat.model.Message;
import com.assignment.interactivechat.model.User;
import com.assignment.interactivechat.repository.MessageRepository;
import com.assignment.interactivechat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public Page<MessageData> listUnseenMessages(String userId, Integer pageNo,
                                                Integer pageSize, String orderBy, String sort) throws ApiException {

        Optional<User> oUser = userRepository.findById(userId);
        if(oUser.isEmpty()){
            throw  new ApiException("USER BY THIS ID DOES NOT EXISTS", ResponseStatus.USER_BY_THIS_ID_NOT_FOUND.getValue());
        }

        final Sort.Direction sortOrder = sort.equals("ascending") ? Sort.Direction.ASC : Sort.Direction.DESC;
        final Sort.Order order = new Sort.Order(sortOrder, orderBy).ignoreCase();

        Page<Message> messageList = messageRepository.findByUserAndMessageStatus(oUser.get(), MessageStatus.UNSEEN,PageRequest.of(pageNo, pageSize, Sort.by(order)));

        List<MessageData> messageDataList= new ArrayList<>();

        for(Message message:messageList){
          MessageData messageData= MessageMapper.INSTANCE.messageToMessageData(message);
            messageDataList.add(messageData);
        }
        Page<MessageData> pageableMessageDataList =
                new PageImpl<>(messageDataList, messageList.getPageable(), messageList.getTotalElements());

        return pageableMessageDataList;
}


    public MessageData markMessageAsSeen(String userId, Long messageId) throws ApiException {
        Optional<User> oUser = userRepository.findById(userId);
        if(oUser.isEmpty()){
            throw new ApiException("User By This Id does not Exist",ResponseStatus.USER_BY_THIS_ID_NOT_FOUND.getValue());
        }
        Optional<Message> oMessage = messageRepository.findById(messageId);
        if (oMessage.isEmpty() || oMessage.get().getMessageStatus().equals(MessageStatus.SEEN)) {
            throw new ApiException("This message does not exists or is already seen", ResponseStatus.USER_MESSAGE_NOT_FOUND.getValue());
        } else {
            Message message= oMessage.get();
            message.setMessageStatus(MessageStatus.SEEN);
            messageRepository.save(message);
            return MessageMapper.INSTANCE.messageToMessageData(message);
        }
    }

      public MessageData sendMessage(String userId, MessageData messageData) throws ApiException {
          Optional<User> user = userRepository.findById(userId);
          if (user.isEmpty()) {
              throw new ApiException("User By This id does not exist", ResponseStatus.USER_BY_THIS_ID_NOT_FOUND.getValue());
          }
             Message message = MessageMapper.INSTANCE.messageDataToMessage(messageData);
              messageRepository.save(message);
          return MessageMapper.INSTANCE.messageToMessageData(message);

      }

}
