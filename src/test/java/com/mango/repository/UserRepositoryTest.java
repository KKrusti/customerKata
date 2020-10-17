package com.mango.repository;

import com.mango.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserRepository.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private static final int USER_ID = 1;

	@Test
	public void withUser_create_userCreated(){
		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@mango.com" );

		User createdUser = userRepository.add(user);

		Assertions.assertEquals(createdUser, user);
	}

	@Test
	public void withExistingUser_update_userUpdated(){
		User initialUser = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@mango.com" );
		initialUser = userRepository.add(initialUser);

		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "PalauSolita", "foobar@mango.com" );
		User updatedUser = userRepository.update(user);

		User userLoaded = userRepository.findById(USER_ID);

		Assertions.assertEquals(updatedUser, userLoaded);
		Assertions.assertNotEquals(updatedUser, initialUser);
	}

}
