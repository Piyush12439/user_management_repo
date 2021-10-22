package com.sapient.trg.exception;

public class UserProfileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserProfileException() {
		
	}
	
	public UserProfileException(String message) {
		super(message);
	}
	
	public UserProfileException(String message,Throwable t ) {
		super(message,t);
	}

}
