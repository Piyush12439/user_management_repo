package com.sapient.trg.exception;

public class RoleException extends Exception{
	

		public RoleException() {
			
		}
		
		public RoleException(String message) {
			super(message);
		}
		
		public RoleException(String message,Throwable t ) {
			super(message,t);
		}
	

}
