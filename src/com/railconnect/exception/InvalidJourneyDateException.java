package com.railconnect.exception;

public class InvalidJourneyDateException extends RuntimeException {

    public InvalidJourneyDateException(String message) {
        super(message);
    }

    public InvalidJourneyDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
