package com.customer.repository;

import com.customer.entity.SloganEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SloganRepository extends JpaRepository<SloganEntity, Long> {

	Optional<List<SloganEntity>> findByCustomerId(Long customerId);

}
