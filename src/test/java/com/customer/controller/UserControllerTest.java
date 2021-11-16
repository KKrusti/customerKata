package com.customer.controller;

import com.customer.controller.request.UserRequest;
import com.customer.controller.request.mapper.UserRequestMapper;
import com.customer.controller.response.UserResponse;
import com.customer.controller.response.mapper.UserResponseMapper;
import com.customer.data.TestData;
import com.customer.useCase.AddUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customer.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

	@Mock
	private AddUser addUser;
	@Mock
	private UserRequestMapper userRequestMapper;
	@Mock
	private UserResponseMapper userResponseMapper;

	private UserController userController;

	@BeforeEach
	void setUp() {
		userController = new UserController(addUser, userRequestMapper, userResponseMapper);
	}

	@Test
	public void should_createUser() throws Exception{
		UserRequest userRequest = TestData.getUserRequest();

		UserResponse response = userController.createUser(userRequest);

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode())

	}

	@Test
	public void withUserData_updateUser_userUpdated() throws Exception {
		var user = TestData.getUser();
		user.setCity("new city");

		when(addUser.addFrom(user)).thenReturn(user);

		mvc.perform(put("/v1/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(createdUserInJson(user)))
			.andExpect(status().isOk());
	}

	private static String createdUserInJson(User user) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(user);
	}

}
