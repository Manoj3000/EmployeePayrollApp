package com.bl.employeepayroll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegisterException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
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
