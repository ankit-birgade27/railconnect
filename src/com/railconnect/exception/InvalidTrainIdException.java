package com.railconnect.exception;

public class InvalidTrainIdException extends RuntimeException{
	public InvalidTrainIdException(String msg) {
		super(msg);
	}
}
