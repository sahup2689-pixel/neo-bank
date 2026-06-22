package com.neo_bank.account_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AccountResponse {

    private Long id;

    private String accountNumber;

    private Long customerId;

    private String accountType;

    private BigDecimal balance;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}