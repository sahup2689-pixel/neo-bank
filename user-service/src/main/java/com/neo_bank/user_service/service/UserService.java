package com.neo_bank.user_service.service;

import com.neo_bank.user_service.dto.LoginRequest;
import com.neo_bank.user_service.dto.LoginResponse;
import com.neo_bank.user_service.dto.UserRegistrationRequest;
import com.neo_bank.user_service.dto.UserResponse;
import com.neo_bank.user_service.entity.User;
import com.neo_bank.user_service.enums.Role;
import com.neo_bank.user_service.enums.UserStatus;
import com.neo_bank.user_service.exception.InvalidCredentialsException;
import com.neo_bank.user_service.exception.UserAlreadyExistsException;
import com.neo_bank.user_service.exception.UserNotFoundException;
import com.neo_bank.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse register(UserRegistrationRequest request) {

        log.info("Registering user with email={}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already registered.");
        }

        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new UserAlreadyExistsException(
                    "Mobile number already registered.");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        log.info("User registered successfully. UserId={}",
                savedUser.getId());

        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        log.info("Login attempt for email={}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found."));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password.");
        }

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new InvalidCredentialsException(
                    "User account is not active.");
        }

        log.info("User logged in successfully. UserId={}",
                user.getId());

        return LoginResponse.builder()
                .token("JWT will be generated here")
                .type("Bearer")
                .message("Login Successful")
                .build();
    }
}