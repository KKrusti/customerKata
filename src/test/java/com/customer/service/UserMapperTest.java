package com.customer.service;

import com.customer.data.TestData;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {

	private UserMapper mapper = Mappers.getMapper(UserMapper.class);

	@Test
	void should_map_from_domain_to_entity() {
		var domain = TestData.getUser();
		var expectedEntity = TestData.getUserEntity();

		var entity = mapper.toEntity(domain);

		assertNotNull(entity);
		assertEquals(expectedEntity, entity);
	}

	@Test
	void should_map_from_entity_to_domain() {
		var entity = TestData.getUserEntity();
		var expectedDomain = TestData.getUser();

		var domain = mapper.toDomain(entity);

		assertNotNull(expectedDomain);
		assertEquals(expectedDomain, domain);
	}
}
