package com.customer.service;

import com.customer.data.TestData;
import com.customer.entity.UserEntity;
import com.customer.domain.User;
import com.customer.repository.UserRepository;
import com.customer.useCase.AddUser;
import com.customer.useCase.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class addUserTest {

	private AddUser addUser;

	@Mock
	private UserRepository userRepository;
	@Mock
	private UserMapper userMapper;

	@BeforeEach
	void setUp() {
		addUser = new AddUser(userRepository, userMapper);
	}

	@Test
	void should_crate_a_new_user(){
		User user = TestData.getUser();
		UserEntity userEntity = TestData.getUserEntity();
		when(userMapper.toEntity(user)).thenReturn(userEntity);
		when(userRepository.save(userEntity)).thenReturn(userEntity);
		when(userMapper.toDomain(userEntity)).thenReturn(user);

		User createdUser = addUser.addFrom(user);

		verify(userRepository, times(1)).save(userEntity);
		assertEquals(createdUser, user);
	}
}
