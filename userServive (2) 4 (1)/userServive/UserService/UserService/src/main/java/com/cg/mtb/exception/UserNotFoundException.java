package com.cg.mtb.exception;

public class UserNotFoundException extends Exception{
	String msg;

	public UserNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
