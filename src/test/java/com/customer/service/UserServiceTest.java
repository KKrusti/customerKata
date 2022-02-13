package com.customer.service;

import com.customer.data.TestData;
import com.customer.entity.UserEntity;
import com.customer.exceptions.TermsAndConditionsNotAcceptedException;
import com.customer.exceptions.UserNotFoundException;
import com.customer.repository.UserRepository;
import com.customer.service.mapper.UserEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.customer.data.TestData.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

	private UserService userService;

	@Mock
	private UserRepository userRepository;
	@Mock
	private UserEntityMapper userEntityMapper;

	@BeforeEach
	void setUp() {
		userService = new UserService(userRepository, userEntityMapper);
	}

	@Test
	void should_create_a_new_user() {
		var user = TestData.getUserWithNoId();
		var expectedUser = TestData.getUser();
		UserEntity userEntity = TestData.getUserEntity();
		when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
		when(userRepository.save(userEntity)).thenReturn(userEntity);
		when(userEntityMapper.toDomain(userEntity)).thenReturn(expectedUser);

		var createdUser = userService.saveUser(user);

		verify(userRepository, times(1)).save(userEntity);
		assertEquals(expectedUser, createdUser);
	}

	@Test
	void should_update_user() {
		var user = TestData.getUser("new City");
		var userEntity = TestData.getUserEntity();
		when(userEntityMapper.toDomain(userEntity)).thenReturn(user);
		when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		var expectedUserEntity = TestData.getUserEntity("new City");

		var result = userService.updateUser(USER_ID, user);

		verify(userRepository, times(1)).save(expectedUserEntity);
		assertEquals(result.getId(), user.getId());
		assertEquals(result.getName(), user.getName());
		assertEquals(result.getSurname(), user.getSurname());
		assertEquals(result.getStreet(), user.getStreet());
		assertEquals(result.getCity(), user.getCity());
		assertEquals(result.getEmail(), user.getEmail());
	}

	@Test
	void should_throw_user_not_found_exception_when_retrieving_not_existing_user() {
		var userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class,
			() -> userService.getUser(userId));
	}

	@Test
	void should_throw_user_not_found_exception_when_updating_non_existing_user() {
		var userId = 5L;
		var user = TestData.getUser();
		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class,
			() -> userService.updateUser(userId, user));
	}

	@Test
	void should_throw_exception_when_no_terms_and_conditions_agreed() {
		var user = TestData.getUser();
		user.setAgreedTermsAndConditions(false);

		assertThrows(TermsAndConditionsNotAcceptedException.class,
			() -> userService.saveUser(user));
	}

}
