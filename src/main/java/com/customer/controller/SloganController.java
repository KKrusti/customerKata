package com.customer.controller;

import com.customer.controller.mapper.SloganDTOMapper;
import com.customer.controller.response.SloganDTO;
import com.customer.domain.Slogan;
import com.customer.service.SloganService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/v1/slogan", produces = MediaType.APPLICATION_JSON_VALUE)
public class SloganController {

	private SloganService sloganService;
	private SloganDTOMapper sloganDTOMapper;

	public SloganController(SloganService sloganService, SloganDTOMapper sloganDTOMapper) {
		this.sloganService = sloganService;
		this.sloganDTOMapper = sloganDTOMapper;
	}

	@PostMapping("/upload")
	public ResponseEntity<SloganDTO> uploadSlogan(@RequestBody Slogan slogan) {
		Slogan response = sloganService.uploadSlogan(slogan);
		return ResponseEntity.status(HttpStatus.CREATED).body(sloganDTOMapper.fromDomain(response));
	}
}
