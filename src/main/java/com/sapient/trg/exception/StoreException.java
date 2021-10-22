package com.sapient.trg.exception;

public class StoreException extends Exception{
	

		public StoreException() {
			
		}
		
		public StoreException(String message) {
			super(message);
		}
		
		public StoreException(String message,Throwable t ) {
			super(message,t);
		}
	

}
