package com.railconnect.exception;

public class InvalidPaymentAmountException extends RuntimeException {

    public InvalidPaymentAmountException(String message) {
        super(message);
    }

    public InvalidPaymentAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
