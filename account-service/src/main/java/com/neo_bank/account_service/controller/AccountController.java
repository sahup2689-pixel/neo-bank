package com.neo_bank.account_service.controller;

import com.neo_bank.account_service.dto.CreateAccountRequest;
import com.neo_bank.account_service.entity.Account;
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
    public Account createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        return accountService.createAccount(request);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {

        return accountService.getAccountById(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
    }
}