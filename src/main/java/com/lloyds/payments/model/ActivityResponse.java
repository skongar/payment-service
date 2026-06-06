package com.lloyds.payments.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ActivityResponse {

    private String from;

    private String to;

    private long count;

    private List<PaymentItem> payments;
}
