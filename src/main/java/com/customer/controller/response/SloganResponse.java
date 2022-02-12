package com.customer.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SloganResponse {
	long id;
	String customerId;
	String slogan;
}
