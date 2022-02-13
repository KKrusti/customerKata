package com.customer.controller;

import com.customer.controller.mapper.UserMapper;
import com.customer.data.TestData;
import com.customer.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.customer.data.TestData.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

	private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	@Mock
	private UserService userService;
	private UserController userController;

	@BeforeEach
	void setUp() {
		this.userController = new UserController(this.userService, this.userMapper);
	}

	@Test
	void should_return_user() {
		var user = TestData.getUser();
		var userResponse = TestData.getUserResponse();
		when(this.userService.getUser(user.getId())).thenReturn(user);

		var response = this.userController.getUser(USER_ID);

		assertNotNull(response);
		assertEquals(userResponse, response);
	}

	@Test
	void should_add_user() {
		var user = TestData.getUser();
		var userReceived = TestData.getUserWithNoId();
		var userRequest = TestData.getUserRequest();
		var expectedUserResponse = TestData.getUserResponse();
		when(this.userService.saveUser(userReceived)).thenReturn(user);

		var response = this.userController.createUser(userRequest);

		assertNotNull(response);
		assertEquals(expectedUserResponse, response);
	}

	@Test
	void should_update_user() {
		var userRequest = TestData.getUserRequest();
		userRequest.setId(1L);
		userRequest.setCity("new city");
		var user = TestData.getUser();
		user.setCity("new city");
		when(this.userService.updateUser(USER_ID, user)).thenReturn(user);

		var response = userController.updateUser(userRequest);

		assertNotNull(response);
	}
}
