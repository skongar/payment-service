package com.lloyds.payments.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @NotNull
    private UUID paymentId;

    @NotBlank
    private String debitAccountId;

    @NotBlank
    private String creditAccountId;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;

    @Size(max = 35)
    private String reference;

    @NotNull
    @PastOrPresent
    private Instant timestamp;
}
