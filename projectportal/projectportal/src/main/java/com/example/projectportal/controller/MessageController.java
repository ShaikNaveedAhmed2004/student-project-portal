package com.example.projectportal.controller;


import com.example.projectportal.model.Message;
import com.example.projectportal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public String sendMessage(@RequestParam Long applicationId,
                              @RequestParam Long senderId,
                              @RequestParam Long receiverId,
                              @RequestParam String content){
        return messageService.sendMessage(applicationId,senderId,receiverId,content);
    }

    @GetMapping("/application/{applicationId}")
    public List<Message> getMessagesByApplication(@PathVariable Long applicationId){
        return messageService.getMessagesByApplication(applicationId);
    }

    @GetMapping("/inbox/{receiverId}")
    public List<Message> getInbox(@PathVariable Long receiverId){
        return messageService.getInbox(receiverId);
    }
}
