package com.lloyds.payments.service;

import com.lloyds.payments.dao.entity.PaymentOutcomeEntity;
import com.lloyds.payments.dao.repo.PaymentOutcomeRepository;
import com.lloyds.payments.model.ActivityResponse;
import com.lloyds.payments.model.PaymentItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final PaymentOutcomeRepository repository;

    public ActivityResponse activity(
            Instant from,
            Instant to) {

        List<PaymentOutcomeEntity> entities =
                repository.findByProcessedAtBetween(
                        from,
                        to);

        List<PaymentItem> payments =
                entities.stream()
                        .map(this::map)
                        .toList();

        return ActivityResponse.builder()
                .from(from.toString())
                .to(to.toString())
                .count(payments.size())
                .payments(payments)
                .build();
    }

    private PaymentItem map(
            PaymentOutcomeEntity entity) {

        return PaymentItem.builder()
                .paymentId(entity.getPaymentId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .status(entity.getStatus())
                .processedAt(entity.getProcessedAt())
                .build();
    }
}
