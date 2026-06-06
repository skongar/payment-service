package com.lloyds.payments.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> accountNotFound(
            AccountNotFoundException ex) {

        return ResponseEntity.status(404)
                .body(Map.of(
                        "error",
                        ex.getMessage()));
    }

    @ExceptionHandler(DuplicatePaymentException.class)
    public ResponseEntity<?> duplicate(
            DuplicatePaymentException ex) {

        return ResponseEntity.status(409)
                .body(Map.of(
                        "error",
                        ex.getMessage()));
    }

    @ExceptionHandler(SuspendedAccountException.class)
    public ResponseEntity<?> suspended(
            SuspendedAccountException ex) {

        return ResponseEntity.status(422)
                .body(Map.of(
                        "error",
                        ex.getMessage()));
    }
}
