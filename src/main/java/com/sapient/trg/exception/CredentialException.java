package com.sapient.trg.exception;

public class CredentialException extends Exception{

	public CredentialException() {
		
	}
	
	public CredentialException(String message) {
		super(message);
	}
	
	public CredentialException(String message,Throwable t ) {
		super(message,t);
	}

}
