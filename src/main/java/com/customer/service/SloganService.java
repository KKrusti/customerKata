package com.customer.service;

import com.customer.exceptions.MaximumSlogansException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SloganService {

	public String uploadFile(MultipartFile file) throws MaximumSlogansException {
		//logic goes here
		return ("File uploaded successfully");
	}
}
