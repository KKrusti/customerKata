package com.customer.service;

import com.customer.data.TestData;
import com.customer.entity.SloganEntity;
import com.customer.repository.SloganRepository;
import com.customer.service.mapper.SloganMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SloganServiceTest {

	private SloganService sloganService;

	@Mock
	private SloganMapper sloganMapper;
	@Mock
	private SloganRepository sloganRepository;

	@BeforeEach
	void setUp() {
		sloganService = new SloganService(sloganRepository, sloganMapper);
	}

	@Test
	void should_upload_a_slogan() {
		var customerId = 1L;
		var slogan = TestData.getSlogan();
		var expectedSlogan = TestData.getSlogan(1L);
		SloganEntity sloganEntity = TestData.getSloganEntity();
		when(sloganRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());
		when(sloganRepository.save(any(SloganEntity.class))).thenReturn(sloganEntity);
		when(sloganMapper.toDomain(any(SloganEntity.class))).thenReturn(expectedSlogan);
		when(sloganMapper.toEntity(slogan)).thenReturn(sloganEntity);

		var response = sloganService.uploadSlogan(slogan);

		verify(sloganRepository, times(1)).findByCustomerId(customerId);
		verify(sloganRepository, times(1)).save(sloganEntity);
		assertEquals(expectedSlogan, response);
	}
}