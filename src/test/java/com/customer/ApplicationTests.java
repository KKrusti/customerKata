package com.customer;

import com.customer.controller.SloganController;
import com.customer.controller.UserController;
import com.customer.controller.mapper.SloganMapper;
import com.customer.controller.mapper.UserMapper;
import com.customer.service.SloganService;
import com.customer.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {

	private UserController userController;
	private SloganController fileController;
	@Mock
	private UserService userService;
	@Mock
	private SloganService sloganService;
	@Mock
	private SloganMapper sloganMapper;
	@Mock
	private UserMapper userMapper;

	@BeforeEach
	void setUp() {
		userController = new UserController(userService, userMapper);
		fileController = new SloganController(sloganService, sloganMapper);
	}

	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
		assertThat(fileController).isNotNull();
	}
}
