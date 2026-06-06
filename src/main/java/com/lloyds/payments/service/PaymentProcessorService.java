package com.lloyds.payments.service;

import com.lloyds.payments.dao.entity.PaymentOutcomeEntity;
import com.lloyds.payments.dao.repo.PaymentOutcomeRepository;
import com.lloyds.payments.model.PaymentEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentProcessorService {
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final PaymentOutcomeRepository repository;

    public PaymentProcessorService(KafkaTemplate<String, PaymentEvent> kafkaTemplate, PaymentOutcomeRepository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
    }

    @Transactional
    public void process(PaymentEvent event) {

        String status = determineStatus(event);

        PaymentOutcomeEntity entity = buildPaymentOutComeEntity(event, status);
        repository.save(entity);

        kafkaTemplate.send(
                "payments.processed",
                event.getDebitAccountId(),
                buildPaymentOutComeEvent(entity));

    }

    private String determineStatus(PaymentEvent event) {

        if(event.getAmount()
                .compareTo(new BigDecimal("250000")) > 0) {

            return "HELD";
        }

        return "PROCESSED";
    }

    private PaymentOutcomeEntity buildPaymentOutComeEntity(PaymentEvent event, String status) {
        return PaymentOutcomeEntity.builder()
                .paymentId(event.getPaymentId())
                .debitAccountId(event.getDebitAccountId())
                .creditAccountId(event.getCreditAccountId())
                .status(status)
                .build();
    }

    private PaymentEvent buildPaymentOutComeEvent(PaymentOutcomeEntity entity) {
        return PaymentEvent.builder()
                .paymentId(entity.getPaymentId())
                .build();
    }
}
