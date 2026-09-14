package com.railconnect.exception;

public class InvalidTrainIdException extends Exception{
	
	public InvalidTrainIdException(String message) {
		
		super(message);
	}


public class InvalidTrainIdException extends RuntimeException{
	public InvalidTrainIdException(String msg) {
		super(msg);

}
