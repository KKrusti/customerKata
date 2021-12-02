package com.customer.service.mapper;

import com.customer.data.TestData;
import com.customer.domain.User;
import com.customer.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserMapperTest {

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
	void should_return_null_if_domain_is_null() {
		User domain = null;

		var entity = mapper.toEntity(domain);

		assertNull(entity);
	}

	@Test
	void should_map_from_entity_to_domain() {
		var entity = TestData.getUserEntity();
		var expectedDomain = TestData.getUser();

		var domain = mapper.toDomain(entity);

		assertNotNull(expectedDomain);
		assertEquals(expectedDomain, domain);
	}

	@Test
	void should_return_null_if_entity_is_null() {
		UserEntity entity = null;

		var domain = mapper.toDomain(entity);

		assertNull(domain);
	}
}
