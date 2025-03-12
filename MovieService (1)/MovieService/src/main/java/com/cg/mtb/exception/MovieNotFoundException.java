package com.cg.mtb.exception;

public class MovieNotFoundException extends Exception{

	public MovieNotFoundException() {
		super();
	}

	String msg;

	public MovieNotFoundException(String message) {
		super(message);
		this.msg = msg;
	}
	
}
