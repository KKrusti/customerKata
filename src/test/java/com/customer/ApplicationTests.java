package com.customer;

import com.customer.controller.SloganController;
import com.customer.controller.UserController;
import com.customer.controller.request.mapper.UserRequestMapper;
import com.customer.controller.response.mapper.UserResponseMapper;
import com.customer.service.SloganService;
import com.customer.useCase.AddUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationTests {

	private UserController userController;
	private SloganController fileController;
	@Mock
	private AddUser addUser;
	@Mock
	private SloganService sloganService;
	@Mock
	private UserResponseMapper userResponseMapper;
	@Mock
	private UserRequestMapper userRequestMapper;

	@BeforeEach
	public void setUp() {
		userController = new UserController(addUser, userRequestMapper, userResponseMapper);
		fileController = new SloganController(sloganService);
	}

	@Test
	public void contextLoads(){
		assertThat(userController).isNotNull();
		assertThat(fileController).isNotNull();
	}
}
