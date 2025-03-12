package com.cg.mtb.exception;

public class MovieShowNotFoundException extends Exception {

	String msg;

	public MovieShowNotFoundException(String message) {
		super(message);
		this.msg = msg;
	}
	public MovieShowNotFoundException() {
		super();
	}
}
