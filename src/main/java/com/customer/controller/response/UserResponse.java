package com.customer.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class UserResponse {

	private Long id;
	private String name;
	private String surname;
	private String street;
	private String city;
	private String email;
}
