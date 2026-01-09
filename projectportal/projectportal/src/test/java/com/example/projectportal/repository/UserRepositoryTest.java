package com.example.projectportal.repository;

import com.example.projectportal.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.checkerframework.framework.qual.DefaultQualifier;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByEmail() {
        User user = new User("Naveed","naveed@gmail.com","123","student");
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertEquals(user.getEmail(),"naveed@gmail.com");
    }

    @Test
    void findByEmailAndPassword() {
        User user = new User("Naveed","naveed@gmail.com","123","student");
        Mockito.when(userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword())).thenReturn(Optional.of(user));
        assertEquals(user.getEmail(),"naveed@gmail.com");
        assertEquals(user.getPassword(),"123");
    }
}