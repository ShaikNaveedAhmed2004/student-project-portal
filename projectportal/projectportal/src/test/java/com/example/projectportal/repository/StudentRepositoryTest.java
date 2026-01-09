package com.example.projectportal.repository;

import com.example.projectportal.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUserId() {
        Student student = new Student("4","CS",2);
        Mockito.when(studentRepository.findByUserId(student.getUserId())).thenReturn(Optional.of(student));
        assertEquals(student.getUserId(),2);
    }
}