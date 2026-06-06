package com.lloyds.payments.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class PaymentItem {

    private UUID paymentId;

    private BigDecimal amount;

    private String currency;

    private String status;

    private Instant processedAt;
}
