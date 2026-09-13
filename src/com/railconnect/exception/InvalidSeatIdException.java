package com.railconnect.exception;

public class InvalidSeatIdException extends RuntimeException{
	public InvalidSeatIdException(String msg) {
		super(msg);
	}
}
