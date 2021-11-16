package com.customer.service;

import com.customer.exceptions.MaximumSlogansException;
import org.springframework.stereotype.Service;

@Service
public class SloganService {

	public String uploadFile(String slogan) throws MaximumSlogansException {
		return ("File uploaded successfully");
	}
}
