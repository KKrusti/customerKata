package com.customer.service;

import com.customer.Application;
import com.customer.data.TestData;
import com.customer.entity.UserEntity;
import com.customer.model.User;
import com.customer.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserService.class, Application.class})
public class UserServiceTest {

	private UserService userService;

	@Mock
	private UserRepository userRepository;
	@Mock
	private UserMapper userMapper;

	@BeforeEach
	void setUp() {
		userService = new UserService(userRepository, userMapper);
	}

	@Test
	public void should_crate_a_new_user(){
		User user = TestData.getUser();
		UserEntity userEntity = TestData.getUserEntity();
		when(userMapper.toEntity(user)).thenReturn(userEntity);
		when(userRepository.save(userEntity)).thenReturn(userEntity);
		when(userMapper.toDomain(userEntity)).thenReturn(user);

		User createdUser = userService.saveOrUpdate(user);

		verify(userRepository, times(1)).save(userEntity);
		assertEquals(createdUser, user);
	}

}
