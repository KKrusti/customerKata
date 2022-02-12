package com.customer.controller.request;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class SloganRequest implements Serializable {

	@ApiParam(name = "Slogan Id")
	private long id;

	@ApiParam
	@NotNull
	private String customerId;

	@ApiParam
	@NotNull
	private String text;

}
