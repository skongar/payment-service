package com.lloyds.payments.exception;

public class SuspendedAccountException extends RuntimeException {

    public SuspendedAccountException(String msg) {
        super(msg);
    }
}
