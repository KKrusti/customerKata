package com.customer.service;

import com.customer.domain.Slogan;
import com.customer.entity.SloganEntity;
import com.customer.exceptions.MaximumSlogansException;
import com.customer.repository.SloganRepository;
import com.customer.service.mapper.SloganEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class SloganService {
    private static final int MAXIMUM_ALLOWED_SLOGANS = 3;

    private final SloganRepository sloganRepository;
    private final SloganEntityMapper sloganEntityMapper;

    public SloganService(SloganRepository sloganRepository, SloganEntityMapper sloganEntityMapper) {
        this.sloganRepository = sloganRepository;
        this.sloganEntityMapper = sloganEntityMapper;
    }

    public Slogan uploadSlogan(Slogan slogan) throws MaximumSlogansException {
        isMaximumReached(slogan.getCustomerId());
        SloganEntity savedSlogan = sloganRepository.save(sloganEntityMapper.toEntity(slogan));
        return sloganEntityMapper.toDomain(savedSlogan);
    }

    private void isMaximumReached(Long customerId) {
        var slogansByCustomer = sloganRepository.findByCustomerId(customerId);
        slogansByCustomer.ifPresent(sloganEntities -> {
            if (sloganEntities.size() >= MAXIMUM_ALLOWED_SLOGANS) {
                throw new MaximumSlogansException(customerId);
            }
        });
    }
}
