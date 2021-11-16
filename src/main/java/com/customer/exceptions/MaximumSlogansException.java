package com.customer.exceptions;

public class MaximumSlogansException extends RuntimeException {

	public MaximumSlogansException(Long customerId) {
		super(String.format("User %s already uploaded the maximum amount of slogans allowed", customerId));
	}
}
