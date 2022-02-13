package com.customer.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class UserResponse {
	long id;
	String name;
	String surname;
	String street;
	String city;
	String email;
	boolean agreedTermsAndConditions;
}
