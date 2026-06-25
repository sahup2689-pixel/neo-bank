package com.neo_bank.account_service.repository;

import com.neo_bank.account_service.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
        extends JpaRepository<Account, Long> {

    boolean existsByAccountNumber(String accountNumber);
}