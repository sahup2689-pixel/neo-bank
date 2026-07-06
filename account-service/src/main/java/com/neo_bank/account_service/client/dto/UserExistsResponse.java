package com.neo_bank.account_service.client.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserExistsResponse {
    private boolean success;
    private String message;
    private Boolean data;
    private LocalDateTime timestamp;
}