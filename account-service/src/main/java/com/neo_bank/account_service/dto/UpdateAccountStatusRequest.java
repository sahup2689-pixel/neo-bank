package com.neo_bank.account_service.dto;

import com.neo_bank.account_service.enums.AccountStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAccountStatusRequest {

    @NotNull(message = "Status is required")
    private AccountStatus status;
}