package com.customer.exceptions;

public class UserUnprocessableException extends RuntimeException {
	public UserUnprocessableException(String userId) {
		super(String.format("User Entity unprocessable. User: %s", userId));
	}
}
