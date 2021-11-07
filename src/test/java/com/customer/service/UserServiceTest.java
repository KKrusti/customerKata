package com.customer.service;

import com.customer.Application;
import com.customer.model.User;
import com.customer.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserService.class, Application.class})
public class UserServiceTest {

	private UserService userService;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository = new UserRepository();
		userService = new UserService(userRepository);
	}

	private static final int USER_ID = 1;

	@Test
	public void withUser_create_userCreated(){
		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@customerKata.com" );
		User createdUser = userService.create(user);
		Assertions.assertEquals(createdUser, user);
	}

	@Test
	public void withUser_update_userUpdated(){
		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@customerKata.com" );
		User createdUser = userService.create(user);
		user = new User(USER_ID,"foo", "bar", "DummyStreet", "PalauSolita", "foobar@customerKata.com" );

		User updatedUser = userService.update(user);
		Assertions.assertNotEquals(updatedUser, createdUser);
		Assertions.assertEquals("PalauSolita", updatedUser.getCity());
	}
}
