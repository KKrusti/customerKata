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
@Table(name = "SLOGAN")
public class SloganEntity {

	@Id
	@SequenceGenerator(
		name = "SLOGAN_GENERATOR",
		sequenceName = "SLOGAN_SEQ",
		allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SLOGAN_GENERATOR")
	private Long sloganId;
	private Long customerId;
	private String slogan;

}
