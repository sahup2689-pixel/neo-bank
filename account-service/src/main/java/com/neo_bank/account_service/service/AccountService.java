package com.neo_bank.account_service.service;

import com.neo_bank.account_service.dto.CreateAccountRequest;
import com.neo_bank.account_service.entity.Account;
import com.neo_bank.account_service.repository.AccountRepository;
import com.neo_bank.account_service.util.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Account createAccount(CreateAccountRequest request) {

        String accountNumber;

        do {
            accountNumber =
                    AccountNumberGenerator.generateAccountNumber();
        }
        while (accountRepository.existsByAccountNumber(accountNumber));

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .customerId(request.getCustomerId())
                .accountType(request.getAccountType())
                .balance(request.getBalance())
                .status("ACTIVE")
                .build();

        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));
    }

    public List<Account> getAllAccounts() {

        return accountRepository.findAll();
    }

    public void deleteAccount(Long id) {

        accountRepository.deleteById(id);
    }
}