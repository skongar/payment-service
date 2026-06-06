package com.lloyds.payments.service;

import com.lloyds.payments.dao.entity.PaymentOutcomeEntity;
import com.lloyds.payments.dao.repo.PaymentOutcomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountHistoryService {

    private final PaymentOutcomeRepository repository;

    public List<PaymentOutcomeEntity> history(
            String accountId) {

        return repository
                .findByDebitAccountIdOrCreditAccountId(
                        accountId,
                        accountId);
    }
}