package com.railconnect.exception;


public class DuplicateTrainNumberException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateTrainNumberException(String message) {
        super(message);
    }

    public DuplicateTrainNumberException(String message, Throwable cause) {
        super(message, cause);
    }

}
