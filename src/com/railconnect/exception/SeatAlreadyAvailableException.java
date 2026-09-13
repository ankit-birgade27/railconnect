package com.railconnect.exception;

public class SeatAlreadyAvailableException extends RuntimeException{
	public SeatAlreadyAvailableException(String msg) {
		super(msg);
	}
}
