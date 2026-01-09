package com.example.projectportal.controller;

import com.example.projectportal.dto.LoginRequest;
import com.example.projectportal.dto.RegisterRequest;
import com.example.projectportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
