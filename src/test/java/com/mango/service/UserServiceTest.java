package com.mango.service;

import com.mango.Application;
import com.mango.model.User;
import com.mango.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserService.class, Application.class})
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	private static final int USER_ID = 1;

	@Test
	public void withUser_create_userCreated(){
		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@mango.com" );
		User createdUser = userService.create(user);
		Assertions.assertEquals(createdUser, user);
	}

	@Test
	public void withUser_update_userUpdated(){
		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@mango.com" );
		User createdUser = userService.create(user);
		user = new User(USER_ID,"foo", "bar", "DummyStreet", "PalauSolita", "foobar@mango.com" );

		User updatedUser = userService.update(user);
		Assertions.assertNotEquals(updatedUser, createdUser);
		Assertions.assertEquals("PalauSolita", updatedUser.getCity());
	}
}
