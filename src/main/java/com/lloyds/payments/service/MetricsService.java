package com.lloyds.payments.service;

import com.lloyds.payments.dao.repo.PaymentOutcomeRepository;
import com.lloyds.payments.model.SummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final PaymentOutcomeRepository repository;

    public SummaryResponse summary() {

        long processed =
                repository.countByStatus("PROCESSED");

        long held =
                repository.countByStatus("HELD");

        return SummaryResponse.builder()
                .totalPayments(processed + held)
                .processedPayments(processed)
                .heldPayments(held)
                .build();
    }
}
