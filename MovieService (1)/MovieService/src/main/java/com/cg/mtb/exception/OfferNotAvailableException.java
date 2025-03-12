package com.cg.mtb.exception;

public class OfferNotAvailableException extends Exception{

	public OfferNotAvailableException() {
		super();
	}
	String msg;
	public OfferNotAvailableException(String message) {
		super(message);
		this.msg = msg;
	}
	

}
