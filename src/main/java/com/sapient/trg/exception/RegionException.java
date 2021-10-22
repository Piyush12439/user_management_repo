package com.sapient.trg.exception;

public class RegionException extends Exception{
	

		public RegionException() {
			
		}
		
		public RegionException(String message) {
			super(message);
		}
		
		public RegionException(String message,Throwable t ) {
			super(message,t);
		}
	

}
