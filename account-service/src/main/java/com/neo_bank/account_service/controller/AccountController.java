package com.neo_bank.account_service.controller;

import com.neo_bank.account_service.dto.AccountResponse;
import com.neo_bank.account_service.dto.CreateAccountRequest;
import com.neo_bank.account_service.dto.UpdateAccountStatusRequest;
import com.neo_bank.account_service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        return accountService.createAccount(request);
    }

    @GetMapping("/{id}")
    public AccountResponse getAccountById(
            @PathVariable Long id) {

        return accountService.getAccountById(id);
    }

    @GetMapping
    public List<AccountResponse> getAllAccounts() {

        return accountService.getAllAccounts();
    }

    @GetMapping("/account-number/{accountNumber}")
    public AccountResponse getAccountByAccountNumber(
            @PathVariable String accountNumber) {

        return accountService.getAccountByAccountNumber(accountNumber);
    }

    @PutMapping("/{id}/status")
    public AccountResponse updateAccountStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAccountStatusRequest request) {

        return accountService.updateAccountStatus(
                id,
                request.getStatus());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeAccount(@PathVariable Long id) {

        accountService.closeAccount(id);
    }
}