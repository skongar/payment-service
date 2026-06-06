package com.lloyds.payments.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payment_outcomes")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaymentOutcomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID paymentId;

    private String debitAccountId;

    private String creditAccountId;

    private BigDecimal amount;

    private String currency;

    private String status;

    private Instant processedAt;

    private long processingTimeMs;

    public PaymentOutcomeEntity() {

    }
}
