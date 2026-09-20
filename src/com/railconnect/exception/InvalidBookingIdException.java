package com.railconnect.exception;

public class InvalidBookingIdException extends RuntimeException {

    public InvalidBookingIdException(String message) {
        super(message);
    }

    public InvalidBookingIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
