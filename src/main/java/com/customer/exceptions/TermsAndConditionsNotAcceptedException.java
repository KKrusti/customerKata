package com.customer.exceptions;

public class TermsAndConditionsNotAcceptedException extends RuntimeException {

	public TermsAndConditionsNotAcceptedException() {
		super("Terms and conditions have not been accepted, user cannot be registered");
	}
}
