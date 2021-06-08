package com.assignment.interactivechat.controller;

import com.assignment.interactivechat.dto.MessageData;
import com.assignment.interactivechat.exception.ApiException;
import com.assignment.interactivechat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public ResponseEntity<Page<MessageData>> getUnseenMessages(@RequestParam("userId") String userId,@RequestParam("pageNo") Integer pageNo,
                                                               @RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy,@RequestParam("sort") String sort)
            throws ApiException {
        Page<MessageData> unseenMessages = messageService.listUnseenMessages(userId, pageNo, pageSize, orderBy, sort);
        return new ResponseEntity<>(unseenMessages, HttpStatus.OK);
    }

    @PutMapping(value = "/read/{userId}/message/{messageId}")
    public ResponseEntity<MessageData> markMessageSeen(@PathVariable("userId") String userId, @PathVariable("messageId") Long messageId) throws ApiException {
        MessageData markMessageSeen = messageService.markMessageAsSeen(userId, messageId);
        return new ResponseEntity<>(markMessageSeen, HttpStatus.OK);
    }

    @PostMapping(value = { "/send/{userId}" })
    public ResponseEntity<String> sendMsg(@PathVariable (name = "userId") String userId,
            @RequestBody MessageData messageData) throws ApiException {
        messageService.sendMessage(userId, messageData);
        return new ResponseEntity<>("Successfully Sent", HttpStatus.OK);
    }
}
