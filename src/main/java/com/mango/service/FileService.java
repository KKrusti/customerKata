package com.mango.service;

import com.mango.exceptions.MaximumSlogansException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String uploadFile(MultipartFile file) throws MaximumSlogansException {
		//logic goes here
		return ("File uploaded successfully");
	}
}