package com.neo_bank.account_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAccountRequest {

    @NotNull
    private Long customerId;

    @NotBlank
    private String accountType;

    @NotNull
    private Double balance;
}