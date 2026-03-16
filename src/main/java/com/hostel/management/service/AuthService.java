package com.hostel.management.service;
import com.hostel.management.dto.request.LoginRequest;
import com.hostel.management.dto.response.JwtResponse;
public interface AuthService {
    JwtResponse login(LoginRequest request);
}
