package com.customer.controller;

import com.customer.domain.Slogan;
import com.customer.service.SloganService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/v1/slogan", produces = MediaType.APPLICATION_JSON_VALUE)
public class SloganController {

	private SloganService sloganService;

	public SloganController(SloganService sloganService) {
		this.sloganService = sloganService;
	}

	@PostMapping("/upload/{customerId}")
	public ResponseEntity<Void> uploadSlogan(@PathVariable Long customerId, @RequestBody Slogan slogan) {
		sloganService.uploadSlogan(customerId, slogan);
		return ResponseEntity.ok().build();
	}
}
