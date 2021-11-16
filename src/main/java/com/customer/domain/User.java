package com.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Long id;
	private String name;
	private String surname;
	private String street;
	private String city;
	private String email;
	private boolean agreedTermsAndConditions;

}