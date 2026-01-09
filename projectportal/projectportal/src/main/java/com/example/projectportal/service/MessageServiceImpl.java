package com.example.projectportal.service;

import com.example.projectportal.model.Message;
import com.example.projectportal.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public String sendMessage(Long applicationId, Long senderId, Long receiverId, String content) {
        Message message = new Message(applicationId,senderId,receiverId,content, LocalDateTime.now());
        messageRepository.save(message);
        return "Message sent Successfully";
    }

    @Override
    public List<Message> getMessagesByApplication(Long applicationId) {
        return messageRepository.findByApplicationId(applicationId);
    }

    @Override
    public List<Message> getInbox(Long receiverId) {
        return messageRepository.findByreceiverId(receiverId);
    }
}
