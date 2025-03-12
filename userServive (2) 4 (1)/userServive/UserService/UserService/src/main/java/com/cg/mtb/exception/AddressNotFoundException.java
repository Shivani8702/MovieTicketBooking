package com.cg.mtb.exception;

public class AddressNotFoundException extends Exception{
	String msg;

	public AddressNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public AddressNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
