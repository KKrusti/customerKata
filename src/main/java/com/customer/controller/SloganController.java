package com.customer.controller;

import com.customer.exceptions.MaximumSlogansException;
import com.customer.service.SloganService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/v1/slogan", produces = MediaType.APPLICATION_JSON_VALUE)
public class SloganController {

	private SloganService sloganService;

	public SloganController(SloganService sloganService) {
		this.sloganService = sloganService;
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file){
		try {
			String response = sloganService.uploadFile(file);
			return ResponseEntity.ok(response);
		} catch (MaximumSlogansException ex){
			return ResponseEntity.ok("Maximum slogans reached");
		}
	}
}
