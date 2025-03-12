package com.cg.mtb.exception;

public class InvalidPaymentStatusException extends RuntimeException {

    public InvalidPaymentStatusException(String message) {
        super(message);
    }
}

