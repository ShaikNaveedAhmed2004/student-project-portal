package com.example.projectportal.service;

import com.example.projectportal.dto.LoginRequest;
import com.example.projectportal.dto.RegisterRequest;
import com.example.projectportal.model.Faculty;
import com.example.projectportal.model.Student;
import com.example.projectportal.model.User;
import com.example.projectportal.repository.FacultyRepository;
import com.example.projectportal.repository.StudentRepository;
import com.example.projectportal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private FacultyRepository facultyRepository ;

    //Mockito.mock(FacultyRepository.class)

    @InjectMocks
    private AuthServiceImpl authServiceImpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterNewStudent() {
        RegisterRequest request = new RegisterRequest("Shaik", "shaik@edu.in", "1234", "STUDENT", "CS", "4");
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        User user = new User("Shaik", "shaik@edu.in", "1234", "STUDENT");
        user.setId(1l);
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        Mockito.when(studentRepository.save(any(Student.class))).thenReturn(new Student());
        String result = authServiceImpl.register(request);
        assertEquals("Registration successful", result);
    }

    @Test
    void testRegisterNewFaculty() {
        RegisterRequest request = new RegisterRequest("bob", "bob@edu.in", "pass123", "FACULTY", "IT");
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        User user = new User("bob", "bob@edu.in", "pass123", "FACULTY");
        user.setId(1l);
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        Mockito.when(facultyRepository.save(any(Faculty.class))).thenReturn(new Faculty());
        String result = authServiceImpl.register(request);
        assertEquals("Registration successful", result);
    }

    @Test
    void testRegisterExistingUser() {
        RegisterRequest request = new RegisterRequest("Naveed", "naveed@gmail.com", "1234", "STUDENT", "CS", "4");
        Mockito.when(userRepository.findByEmail("naveed@gmail.com")).thenReturn(Optional.of(new User()));
        String result = authServiceImpl.register(request);
        assertEquals("User already exists", result);
    }

    @Test
    void testInvalidRole() {
        RegisterRequest request = new RegisterRequest("Naveed", "naveed@edu.in", "pass123", "ADMIN", null, null);
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        String result = authServiceImpl.register(request);

        assertEquals("Invalid role specified", result);
    }

    @Test
    void testRegisterStudentMissingBranchYear() {
        RegisterRequest request = new RegisterRequest("Zaid", "zaid@edu.in", "pass123", "STUDENT", null, null);
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        String result = authServiceImpl.register(request);

        assertEquals("Please Enter Branch and Year", result);

    }

    @Test
    void testRegisterFacultyMissingDepartment() {
        RegisterRequest request = new RegisterRequest("Sameer", "sameer@edu.in", "pass123", "FACULTY", null);
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        String result = authServiceImpl.register(request);

        assertEquals("Please Enter Department", result);

    }

    @Test
    void testLoginSuccess() {
        User user = new User();
        user.setEmail("login@edu.in");
        user.setPassword("1234");

        Mockito.when(userRepository.findByEmail("login@edu.in")).thenReturn(Optional.of(user));

        String result = authServiceImpl.login(new LoginRequest("login@edu.in", "1234"));

        assertEquals("Login successful", result);
    }

    @Test
    void testLoginInvalidPassword() {
        User user = new User();
        user.setEmail("login@edu.in");
        user.setPassword("correct123");

        Mockito.when(userRepository.findByEmail("login@edu.in")).thenReturn(Optional.of(user));

        String result = authServiceImpl.login(new LoginRequest("login@edu.in", "wrong123"));

        assertEquals("Invalid credentials", result);
    }

    @Test
    void testInvalid(){

        Mockito.when(userRepository.findByEmail("nouser@edu.in")).thenReturn(Optional.empty());

        String result = authServiceImpl.login(new LoginRequest("nouser@edu.in", "1234"));

        assertEquals("Invalid credentials", result);
    }
}