package com.customer.repository;

import com.customer.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserRepository.class)
public class UserRepositoryTest {

	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository = new UserRepository();
	}

	private static final int USER_ID = 1;

	@Test
	public void withUser_create_userCreated(){
		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@customerKata.com" );

		User createdUser = userRepository.add(user);

		Assertions.assertEquals(createdUser, user);
	}

	@Test
	public void withExistingUser_update_userUpdated(){
		User initialUser = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@customerKata.com" );
		initialUser = userRepository.add(initialUser);

		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "PalauSolita", "foobar@customerKata.com" );
		User updatedUser = userRepository.update(user);

		User userLoaded = userRepository.findById(USER_ID);

		Assertions.assertEquals(updatedUser, userLoaded);
		Assertions.assertNotEquals(updatedUser, initialUser);
	}

}
