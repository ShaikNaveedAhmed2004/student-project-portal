package com.example.projectportal.service;

import com.example.projectportal.dto.LoginRequest;
import com.example.projectportal.dto.RegisterRequest;

public interface AuthService {
    public String register(RegisterRequest request);
    public String login(LoginRequest request);
}
