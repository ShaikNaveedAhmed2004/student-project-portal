package com.example.projectportal.service;

import com.example.projectportal.dto.*;
import com.example.projectportal.model.*;
import com.example.projectportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    @Transactional
    public String register(RegisterRequest request) {
        try {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                return "User already exists";
            }

            String role = request.getRole().toUpperCase();
            if (!request.getRole().equalsIgnoreCase("STUDENT") &&
                    !request.getRole().equalsIgnoreCase("FACULTY")) {
                return "Invalid role specified";
            }
            if("STUDENT".equals(role)){
                if(request.getBranch() == null || request.getYear() == null){
                    return "Please Enter Branch and Year";
                }
            } else if ("FACULTY".equals(role)) {
                if(request.getDepartment() == null ){
                    return "Please Enter Department";
                }
            }

            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setRole(request.getRole());

            User savedUser = userRepository.save(user);
            Long userId = savedUser.getId();

            if ("STUDENT".equalsIgnoreCase(role)) {
                Student student = new Student();
                student.setUserId(userId);
                student.setBranch(request.getBranch());
                student.setYear(request.getYear());
                studentRepository.save(student);
            } else if ("FACULTY".equalsIgnoreCase(role)) {
                Faculty faculty = new Faculty();
                faculty.setUserId(userId);
                faculty.setDepartment(request.getDepartment());
                facultyRepository.save(faculty);
            }


            return "Registration successful";

        } catch (Exception e) {
            System.err.println("Registration error: " + e.getMessage());
            return "Registration failed";
        }
    }

    @Override
    public String login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return "Invalid credentials";
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(request.getPassword())) {
            return "Invalid credentials";
        }

        return "Login successful";
    }
}