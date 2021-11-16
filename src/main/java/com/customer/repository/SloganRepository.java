package com.customer.repository;

import com.customer.entity.SloganEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SloganRepository extends JpaRepository<SloganEntity, Long> {

	int countByCustomerId(Long customerId);
}
