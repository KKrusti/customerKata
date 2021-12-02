package com.customer.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SloganDTO {

	Long id;
	String customerId;
	String slogan;
}
