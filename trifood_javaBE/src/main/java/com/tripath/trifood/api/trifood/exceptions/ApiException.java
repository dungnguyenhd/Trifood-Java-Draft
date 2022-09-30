package com.tripath.trifood.api.trifood.exceptions;

public class ApiException extends RuntimeException {
	public ApiException(String message) {
		super(message);
	}

	public ApiException() {
		super();
	}

}
