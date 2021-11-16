package com.customer.controller;

import com.customer.data.TestData;
import com.customer.domain.User;
import com.customer.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.customer.data.TestData.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

	@Mock
	private UserService userService;

	private UserController userController;

	@BeforeEach
	void setUp() {
		this.userController = new UserController(this.userService);
	}

	@Test
	void should_add_user() {
		var user = TestData.getUserWithNoId();
		when(this.userService.saveUser(user)).thenReturn(user);

		ResponseEntity<User> response = this.userController.createUser(user);

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	void should_return_user() {
		var user = TestData.getUser();
		when(this.userService.getUser(user.getId())).thenReturn(user);

		ResponseEntity<User> response = this.userController.getUser(USER_ID);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	void should_update_user() {
		var user = TestData.getUser();
		user.setCity("new city");
		when(this.userService.updateUser(USER_ID, user)).thenReturn(user);

		ResponseEntity<Void> response = userController.updateUser(user);

		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}
