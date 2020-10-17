package com.mango;

import com.mango.controller.FileController;
import com.mango.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private FileController fileController;


	@Test
	public void contextLoads(){
		assertThat(userController).isNotNull();
		assertThat(fileController).isNotNull();
	}
}
