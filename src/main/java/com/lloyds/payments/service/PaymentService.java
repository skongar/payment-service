package com.lloyds.payments.service;

import com.lloyds.payments.dao.entity.AccountEntity;
import com.lloyds.payments.dao.entity.ProcessedPaymentEntity;
import com.lloyds.payments.dao.repo.AccountRepository;
import com.lloyds.payments.dao.repo.ProcessedPaymentRepository;
import com.lloyds.payments.exception.DuplicatePaymentException;
import com.lloyds.payments.exception.SuspendedAccountException;
import com.lloyds.payments.model.PaymentEvent;
import com.lloyds.payments.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final AccountRepository accountRepository;

    private final ProcessedPaymentRepository processedPaymentRepository;

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Transactional
    public UUID submit(PaymentRequest request) throws DuplicatePaymentException{

        validateAccounts(request);

        if (processedPaymentRepository.existsById(
                request.getPaymentId())) {

            throw new DuplicatePaymentException(
                    "Duplicate payment");
        }

        PaymentEvent event =
                PaymentEvent.builder()
                        .paymentId(request.getPaymentId())
                        .debitAccountId(request.getDebitAccountId())
                        .creditAccountId(request.getCreditAccountId())
                        .amount(request.getAmount())
                        .currency(request.getCurrency())
                        .timestamp(request.getTimestamp())
                        .build();

        kafkaTemplate.send(
                "payments.submitted",
                request.getDebitAccountId(),
                event);

        processedPaymentRepository.save(
                ProcessedPaymentEntity.builder()
                        .paymentId(request.getPaymentId())
                        .createdAt(Instant.now())
                        .build());

        return request.getPaymentId();
    }

    private void validateAccounts(
            PaymentRequest request) {

        AccountEntity debit =
                accountRepository.findById(
                                request.getDebitAccountId())
                        .orElseThrow();

        AccountEntity credit =
                accountRepository.findById(
                                request.getCreditAccountId())
                        .orElseThrow();

        if ("SUSPENDED".equals(debit.getStatus())) {
            throw new SuspendedAccountException(
                    debit.getAccountId());
        }

        if ("SUSPENDED".equals(credit.getStatus())) {
            throw new SuspendedAccountException(
                    credit.getAccountId());
        }
    }
}
