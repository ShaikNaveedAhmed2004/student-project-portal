package com.example.projectportal.service;

import com.example.projectportal.model.Message;

import java.util.List;

public interface MessageService {
    String sendMessage(Long applicationId,Long senderId,Long receiverId,String content);
    List<Message> getMessagesByApplication(Long applicationId);
    List<Message> getInbox(Long receiverId);
}
