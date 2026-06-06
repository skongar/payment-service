package com.lloyds.payments.exception;

public class DuplicatePaymentException extends RuntimeException {

    public DuplicatePaymentException(String msg) {
        super(msg);
    }
}
