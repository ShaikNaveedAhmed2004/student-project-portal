package com.example.projectportal.service;

import com.example.projectportal.model.Message;
import com.example.projectportal.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        Long applicationId = 1L;
        Long senderId = 100L;
        Long receiverId = 200L;
        String content = "Hello Faculty";
        String result = messageService.sendMessage(applicationId, senderId, receiverId, content);

        assertEquals("Message sent Successfully", result);
    }

    @Test
    void testGetMessagesByApplication() {
        Long applicationId = 1L;

        Message msg1 = new Message(applicationId, 101L, 102L, "Message 1", LocalDateTime.now());
        Message msg2 = new Message(applicationId, 102L, 101L, "Message 2", LocalDateTime.now());

        Mockito.when(messageRepository.findByApplicationId(applicationId)).thenReturn(Arrays.asList(msg1, msg2));

        List<Message> messages = messageService.getMessagesByApplication(applicationId);

        assertEquals(2, messages.size());
        assertEquals("Message 1", messages.get(0).getContent());
        assertEquals("Message 2", messages.get(1).getContent());
    }

    @Test
    void testGetInbox() {
        Long receiverId = 200L;

        Message msg1 = new Message(2L, 100L, receiverId, "Inbox Message 1", LocalDateTime.now());
        Message msg2 = new Message(3L, 101L, receiverId, "Inbox Message 2", LocalDateTime.now());

        Mockito.when(messageRepository.findByreceiverId(receiverId)).thenReturn(Arrays.asList(msg1, msg2));

        List<Message> inbox = messageService.getInbox(receiverId);

        assertEquals(2, inbox.size());
        assertEquals("Inbox Message 1", inbox.get(0).getContent());
        assertEquals(receiverId, inbox.get(0).getReceiverId());
    }
}

