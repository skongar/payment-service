package com.lloyds.payments.controller.processor;

import com.lloyds.payments.dao.entity.PaymentOutcomeEntity;
import com.lloyds.payments.service.AccountHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountHistoryController {

    private final AccountHistoryService service;

    @GetMapping("/{accountId}/history")
    public List<PaymentOutcomeEntity> history(
            @PathVariable String accountId) {

        return service.history(accountId);
    }
}
