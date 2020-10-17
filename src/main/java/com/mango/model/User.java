package com.mango.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
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
