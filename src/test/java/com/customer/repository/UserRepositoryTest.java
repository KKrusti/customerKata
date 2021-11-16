package com.customer.repository;

import com.customer.data.TestData;
import com.customer.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Sql("/schema.sql")
	void should_create_user(){
		var userEntity= TestData.getUserEntity();

		UserEntity savedUser = userRepository.save(userEntity);

		assertNotNull(savedUser);
		assertEquals("Eren", savedUser.getName());
		assertEquals("Jaegger", savedUser.getSurname());
		assertEquals("shiganshima", savedUser.getStreet());
		assertEquals("RoseWall", savedUser.getCity());
		assertEquals("shingekinokyojin@manga.com", savedUser.getEmail());
	}

}
