package com.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private int id;
	private String name;
	private String surname;
	private String street;
	private String city;
	private String email;

}
