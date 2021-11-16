package com.customer.service;

import com.customer.domain.Slogan;
import com.customer.exceptions.MaximumSlogansException;
import com.customer.repository.SloganRepository;
import org.springframework.stereotype.Service;

@Service
public class SloganService {
	private static final int MAXIMUM_ALLOWED_SLOGANS = 3;

	private SloganRepository sloganRepository;

	public SloganService(SloganRepository sloganRepository) {
		this.sloganRepository = sloganRepository;
	}

	public String uploadSlogan(Long customerId, Slogan slogan) throws MaximumSlogansException {
		isMaximumReached(customerId);
		return null;
	}

	private void isMaximumReached(Long customerId) {
		if (sloganRepository.countByCustomerId(customerId) < MAXIMUM_ALLOWED_SLOGANS) {
			throw new MaximumSlogansException(customerId);
		}
	}
}
