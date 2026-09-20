package com.railconnect.exception;

public class InvalidStationException extends RuntimeException {

    public InvalidStationException(String message) {
        super(message);
    }

    public InvalidStationException(String message, Throwable cause) {
        super(message, cause);
    }
}
