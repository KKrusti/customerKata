package com.customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customer.model.User;
import com.customer.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@MockBean
	private UserService mockUserService;

	@Autowired
	private MockMvc mvc;

	private static final int USER_ID = 1;

	@Test
	public void withUserData_createUser_userCreated() throws Exception{
		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes", "foobar@mango.com" );

		when(mockUserService.create(user)).thenReturn(user);

		mvc.perform(post("/v1/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(createdUserInJson(user)))
			.andExpect(status().isCreated());
	}

	@Test
	public void withUserData_updateUser_userUpdated() throws Exception {
		User user = new User(USER_ID,"foo", "bar", "DummyStreet", "Montornes2", "foobar@mango.com" );

		when(mockUserService.update(user)).thenReturn(user);

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
