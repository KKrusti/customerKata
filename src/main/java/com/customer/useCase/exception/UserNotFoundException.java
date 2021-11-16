package com.customer.useCase.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long userId) {
		super(String.format("User %s does not exist", userId));
	}
}
