package com.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_INFO")
public class UserEntity {

	@Id
	@SequenceGenerator(
		name = "CUSTOMER_INFO_GENERATOR",
		sequenceName = "CUSTOMER_INFO_SEQ",
		allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_INFO_GENERATOR")
	private Long id;

	private String name;
	private String surname;
	private String street;
	private String city;
	private String email;
	private boolean termsAndConditions;

}
