package com.hostel.management.controller;

import com.hostel.management.dto.request.LoginRequest;
import com.hostel.management.dto.response.ApiResponse;
import com.hostel.management.dto.response.JwtResponse;
import com.hostel.management.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * POST /api/auth/login
     * Public endpoint — returns JWT token on successful authentication.
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@Valid @RequestBody LoginRequest request) {
        JwtResponse jwtResponse = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", jwtResponse));
    }
}
