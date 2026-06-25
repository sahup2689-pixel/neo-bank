package com.neo_bank.account_service.service;

import com.neo_bank.account_service.dto.AccountResponse;
import com.neo_bank.account_service.dto.CreateAccountRequest;
import com.neo_bank.account_service.entity.Account;
import com.neo_bank.account_service.enums.AccountStatus;
import com.neo_bank.account_service.exception.AccountNotFoundException;
import com.neo_bank.account_service.repository.AccountRepository;
import com.neo_bank.account_service.util.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;

    // ==========================
    // CREATE ACCOUNT
    // ==========================
    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {

        log.info("Creating account for customerId={}",
                request.getCustomerId());

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
                .balance(request.getInitialBalance())
                .status(AccountStatus.ACTIVE)
                .build();

        Account savedAccount =
                accountRepository.save(account);

        log.info("Account created successfully. Account Number={}",
                savedAccount.getAccountNumber());

        return mapToResponse(savedAccount);
    }

    // ==========================
    // GET ACCOUNT BY ID
    // ==========================
    public AccountResponse getAccountById(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found with id: " + id));

        return mapToResponse(account);
    }

    // ==========================
    // GET ACCOUNT BY NUMBER
    // ==========================
    public AccountResponse getAccountByAccountNumber(
            String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found with account number: "
                                        + accountNumber));

        return mapToResponse(account);
    }

    // ==========================
    // GET ALL ACCOUNTS
    // ==========================
    public List<AccountResponse> getAllAccounts() {

        return accountRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // GET ACCOUNTS BY CUSTOMER
    // ==========================
    public List<AccountResponse> getAccountsByCustomerId(
            Long customerId) {

        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // UPDATE ACCOUNT STATUS
    // ==========================
    @Transactional
    public AccountResponse updateAccountStatus(
            Long id,
            AccountStatus status) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found with id: " + id));

        account.setStatus(status);

        Account updatedAccount =
                accountRepository.save(account);

        log.info(
                "Account status updated. AccountId={}, NewStatus={}",
                id,
                status
        );

        return mapToResponse(updatedAccount);
    }

    // ==========================
    // CLOSE ACCOUNT (SOFT DELETE)
    // ==========================
    @Transactional
    public void closeAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found with id: " + id));

        account.setStatus(AccountStatus.CLOSED);

        accountRepository.save(account);

        log.info("Account closed successfully. AccountId={}", id);
    }

    // ==========================
    // DTO MAPPER
    // ==========================
    private AccountResponse mapToResponse(Account account) {

        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .accountType(account.getAccountType().name())
                .balance(account.getBalance())
                .status(account.getStatus().name())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }
}