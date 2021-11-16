package com.customer.exceptions;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(Long userId) {
		super(String.format("User not found for given id:  %s.", userId));
	}
}
