package com.lloyds.payments.dao.repo;

import com.lloyds.payments.dao.entity.PaymentOutcomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface PaymentOutcomeRepository
        extends JpaRepository<PaymentOutcomeEntity, Long> {

    long countByStatus(String status);

    List<PaymentOutcomeEntity> findByProcessedAtBetween(
            Instant from,
            Instant to);

    List<PaymentOutcomeEntity>
    findByDebitAccountIdOrCreditAccountId(
            String debitAccountId,
            String creditAccountId);
}
