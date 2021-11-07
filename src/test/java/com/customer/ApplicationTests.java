package com.customer;

import com.customer.controller.FileController;
import com.customer.controller.UserController;
import com.customer.service.FileService;
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
public class ApplicationTests {

	private UserController userController;
	private FileController fileController;
	@Mock
	private UserService userService;
	@Mock
	private FileService fileService;

	@BeforeEach
	public void setUp() {
		userController = new UserController(userService);
		fileController = new FileController(fileService);
	}

	@Test
	public void contextLoads(){
		assertThat(userController).isNotNull();
		assertThat(fileController).isNotNull();
	}
}
