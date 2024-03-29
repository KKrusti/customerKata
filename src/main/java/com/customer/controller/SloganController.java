package com.customer.controller;

import com.customer.controller.exception.ApiError;
import com.customer.controller.mapper.SloganMapper;
import com.customer.controller.request.SloganRequest;
import com.customer.controller.response.SloganResponse;
import com.customer.domain.Slogan;
import com.customer.service.SloganService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/v1/slogan", produces = MediaType.APPLICATION_JSON_VALUE)
public class SloganController {

	private final SloganService sloganService;
	private final SloganMapper sloganMapper;

	public SloganController(SloganService sloganService, SloganMapper sloganMapper) {
		this.sloganService = sloganService;
		this.sloganMapper = sloganMapper;
	}

	@ApiOperation(value = "Create a slogan for a user")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Slogan Added"),
		@ApiResponse(code = 403, message = "Maximum slogans reached", response = ApiError.class),
	})
	@PostMapping("/upload")
	@ResponseStatus(HttpStatus.CREATED)
	public SloganResponse uploadSlogan(@RequestBody SloganRequest sloganRequest) {
		Slogan response = sloganService.uploadSlogan(sloganMapper.toDomain(sloganRequest));
		return sloganMapper.fromDomain(response);
	}
}
