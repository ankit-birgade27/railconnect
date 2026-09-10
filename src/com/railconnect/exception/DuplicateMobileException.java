package com.railconnect.exception;

public class DuplicateMobileException extends RuntimeException {

    public DuplicateMobileException(String message) {
        super(message);
    }

    public DuplicateMobileException(String message, Throwable cause) {
        super(message, cause);
    }
}
