package com.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Slogan {

	private Long id;
	private Long customerId;
	private String sloganText;
}
