package com.railconnect.exception;

public class RouteNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RouteNotFoundException(String message) {
        super(message);
    }

    public RouteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

public class RouteNotFoundException extends Exception{

	public RouteNotFoundException(String message) {
		
		super(message);
	}

}
