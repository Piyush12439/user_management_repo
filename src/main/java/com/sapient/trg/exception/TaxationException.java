package com.sapient.trg.exception;

public class TaxationException extends Exception{
	

		public TaxationException() {
			
		}
		
		public TaxationException(String message) {
			super(message);
		}
		
		public TaxationException(String message,Throwable t ) {
			super(message,t);
		}
	

}
