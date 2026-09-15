package com.railconnect.exception;

public class SeatNotFoundException extends RuntimeException{
	public SeatNotFoundException(String msg) {
		super(msg);
	}

}