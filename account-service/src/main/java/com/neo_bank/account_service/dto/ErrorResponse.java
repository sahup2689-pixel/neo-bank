package com.neo_bank.account_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private String error;
    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;
}