package com.lloyds.payments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOutcomeEvent {

    private UUID paymentId;

    private String debitAccountId;

    private String creditAccountId;

    private BigDecimal amount;

    private String currency;

    private String status;

    private Instant processedAt;

    private long processingTimeMs;
}
