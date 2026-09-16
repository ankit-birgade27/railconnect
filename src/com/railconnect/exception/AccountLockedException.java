package com.railconnect.exception;

public class AccountLockedException extends RuntimeException{
	public AccountLockedException(String msg) {
		super(msg);
	}
}
