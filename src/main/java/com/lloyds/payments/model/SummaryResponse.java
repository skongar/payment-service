package com.lloyds.payments.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SummaryResponse {

    private long totalPayments;

    private long processedPayments;

    private long heldPayments;
}
