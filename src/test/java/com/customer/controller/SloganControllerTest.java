package com.customer.controller;

import com.customer.controller.mapper.SloganDTOMapper;
import com.customer.data.TestData;
import com.customer.domain.Slogan;
import com.customer.service.SloganService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SloganControllerTest {

	@Mock
	private SloganService sloganService;
	@Mock
	private SloganDTOMapper sloganDTOMapper;

	private SloganController sloganController;

	@BeforeEach
	void setUp() {
		sloganController = new SloganController(sloganService, sloganDTOMapper);
	}

	@Test
	void should_upload_a_slogan() {
		Slogan slogan = TestData.getSlogan();
		when(sloganService.uploadSlogan(slogan)).thenReturn(slogan);

		var response = sloganController.uploadSlogan(slogan);

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
}
