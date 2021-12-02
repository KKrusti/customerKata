package com.customer.service;

import com.customer.domain.Slogan;
import com.customer.entity.SloganEntity;
import com.customer.exceptions.MaximumSlogansException;
import com.customer.repository.SloganRepository;
import com.customer.service.mapper.SloganMapper;
import org.springframework.stereotype.Service;

@Service
public class SloganService {
	private static final int MAXIMUM_ALLOWED_SLOGANS = 3;

	private SloganRepository sloganRepository;
	private SloganMapper sloganMapper;

	public SloganService(SloganRepository sloganRepository, SloganMapper sloganMapper) {
		this.sloganRepository = sloganRepository;
		this.sloganMapper = sloganMapper;
	}

	public Slogan uploadSlogan(Slogan slogan) throws MaximumSlogansException {
		isMaximumReached(slogan.getCustomerId());
		SloganEntity savedSlogan = sloganRepository.save(sloganMapper.toEntity(slogan));
		return sloganMapper.toDomain(savedSlogan);
	}

	private void isMaximumReached(Long customerId) {
		var slogansByCustomer = sloganRepository.findByCustomerId(customerId);
		slogansByCustomer.ifPresent(sloganEntities -> {
			if (sloganEntities.size() <= MAXIMUM_ALLOWED_SLOGANS) {
				throw new MaximumSlogansException(customerId);
			}
		});
	}
}
