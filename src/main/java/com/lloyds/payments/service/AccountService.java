package com.lloyds.payments.service;

import com.lloyds.payments.dao.entity.AccountEntity;
import com.lloyds.payments.dao.repo.AccountRepository;
import com.lloyds.payments.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountEntity findAccount(String accountId) {

        return repository.findById(accountId)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found: "
                                        + accountId));
    }
}
