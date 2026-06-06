package com.lloyds.payments.controller.ingestor;

import com.lloyds.payments.dao.entity.AccountEntity;
import com.lloyds.payments.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/{accountId}")
    public AccountEntity account(
            @PathVariable String accountId) {

        return service.findAccount(accountId);
    }
}
