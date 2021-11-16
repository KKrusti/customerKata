package com.customer.controller;

import com.customer.data.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customer.domain.User;
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
class UserControllerTest {

	@MockBean
	private UserService mockUserService;

	@Autowired
	private MockMvc mvc;

	@Test
	void withUserData_createUser_userCreated() throws Exception{
		User user = TestData.getUser();

		when(mockUserService.saveUser(user)).thenReturn(user);

		mvc.perform(post("/v1/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(createdUserInJson(user)))
			.andExpect(status().isCreated());
	}

	@Test
	void withUserData_updateUser_userUpdated() throws Exception {
		var user = TestData.getUser();
		user.setCity("new city");

		when(mockUserService.saveUser(user)).thenReturn(user);

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
