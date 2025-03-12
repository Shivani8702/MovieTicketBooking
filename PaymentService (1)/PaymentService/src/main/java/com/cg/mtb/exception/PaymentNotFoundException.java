package com.cg.mtb.exception;

public class PaymentNotFoundException extends Exception {

	public PaymentNotFoundException() {
		super();
	}

	String msg;

	public PaymentNotFoundException(String message) {
		super(message);
		this.msg = msg;
	}
}
