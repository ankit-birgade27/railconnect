package com.railconnect.exception;

public class DuplicateUsernameException extends RuntimeException{
	public DuplicateUsernameException(String msg) {
		super(msg);
	}
}
