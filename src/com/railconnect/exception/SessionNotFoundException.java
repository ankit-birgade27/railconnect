package com.railconnect.exception;

public class SessionNotFoundException extends RuntimeException{
	public SessionNotFoundException(String msg) {
		super(msg);
	}
}
