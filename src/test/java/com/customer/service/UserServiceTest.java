package com.customer.service;

import com.customer.Application;
import com.customer.data.TestData;
import com.customer.entity.UserEntity;
import com.customer.exceptions.TermsAndConditionsNotAcceptedException;
import com.customer.domain.User;
import com.customer.exceptions.UserNotFoundException;
import com.customer.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserService.class, Application.class})
class UserServiceTest {

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
	void should_crate_a_new_user(){
		User user = TestData.getUser();
		UserEntity userEntity = TestData.getUserEntity();
		when(userMapper.toEntity(user)).thenReturn(userEntity);
		when(userRepository.save(userEntity)).thenReturn(userEntity);
		when(userMapper.toDomain(userEntity)).thenReturn(user);

		User createdUser = userService.saveUser(user);

		verify(userRepository, times(1)).save(userEntity);
		assertEquals(createdUser, user);
	}

	@Test
	void should_throw_user_not_found_exception_when_retrieving_not_existing_user() {
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class,
			() -> userService.getUser(userId));
	}

	@Test
	void should_throw_exception_when_no_terms_and_conditions_agreed(){
		User user = TestData.getUser();
		user.setAgreedTermsAndConditions(false);

		assertThrows(TermsAndConditionsNotAcceptedException.class,
			()-> userService.saveUser(user));
	}

}
