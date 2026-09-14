package com.railconnect.exception;


public class SeatNotFoundException extends Exception{
	
	public SeatNotFoundException(String message) {
		
		super(message);
	}

public class SeatNotFoundException extends RuntimeException{
	public SeatNotFoundException(String msg) {
		super(msg);
	}

}
