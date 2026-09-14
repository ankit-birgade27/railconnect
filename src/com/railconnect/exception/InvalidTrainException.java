package com.railconnect.exception;


public class InvalidTrainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidTrainException(String message) {
        super(message);
    }

    public InvalidTrainException(String message, Throwable cause) {
        super(message, cause);
    }

public class InvalidTrainException extends Exception{

	public InvalidTrainException(String message) {
		super(message);
	}

}
