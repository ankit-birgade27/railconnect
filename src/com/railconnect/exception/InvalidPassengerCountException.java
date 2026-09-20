package com.railconnect.exception;

public class InvalidPassengerCountException extends RuntimeException {

    public InvalidPassengerCountException(String message) {
        super(message);
    }
}