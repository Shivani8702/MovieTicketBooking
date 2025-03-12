package com.cg.mtb.exception;

public class TicketNotAvailableException extends Exception {
    public TicketNotAvailableException(String message) {
        super(message);
    }

	public TicketNotAvailableException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketNotAvailableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TicketNotAvailableException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TicketNotAvailableException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
    
}

