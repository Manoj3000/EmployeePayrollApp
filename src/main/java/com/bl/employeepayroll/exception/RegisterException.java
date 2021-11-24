package com.bl.employeepayroll.exception;

public class RegisterException extends Exception {
	public String message;
	public long errorcode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(long errorcode) {
		this.errorcode = errorcode;
	}

	public RegisterException(String message, long errorcode) {
		super();
		this.message = message;
		this.errorcode = errorcode;
	}
}
