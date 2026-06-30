package com.neo_bank.user_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private long id;

    private String fullName;

    private String email;

    private String mobileNumber;

    private String role;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
