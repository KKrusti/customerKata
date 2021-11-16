package com.customer.controller.request;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class UserRequest implements Serializable {

	@ApiParam(name = "User ID")
	private Long id;

	@ApiParam(required = true)
	@NotNull
	private String name;

	@ApiParam(required = true)
	@NotNull
	private String surname;

	@ApiParam(required = true)
	@NotNull
	private String street;

	@ApiParam(required = true)
	@NotNull
	private String city;

	@ApiParam(required = true)
	@NotNull
	private String email;
}
