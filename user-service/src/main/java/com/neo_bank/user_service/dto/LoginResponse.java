package com.neo_bank.user_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;

    private String type;

    private String message;
}
