package com.neo_bank.user_service.controller;

import com.neo_bank.user_service.dto.LoginRequest;
import com.neo_bank.user_service.dto.LoginResponse;
import com.neo_bank.user_service.dto.UserRegistrationRequest;
import com.neo_bank.user_service.dto.UserResponse;
import com.neo_bank.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ==========================
    // REGISTER USER
    // ==========================
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody UserRegistrationRequest request) {

        UserResponse response = userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // ==========================
    // LOGIN USER
    // ==========================
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }

    // ==========================
    // TEST ENDPOINT
    // ==========================
    @GetMapping("/test")
    public ResponseEntity<String> test() {

        return ResponseEntity.ok(
                "User Service Running Successfully");
    }
}