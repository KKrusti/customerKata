package com.mango.controller;

import com.mango.model.User;
import com.mango.service.FileService;
import com.mango.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class FileControllerTest {

	@MockBean
	private FileService mockFileService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void withNoMaxSlogans_upload_uploaded(){
		MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
			null, "test data".getBytes());

//
//		when(mockFileService.uploadFile(file)).thenReturn(file);
//
//		mvc.perform(post("/v1/users")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(createdUserInJson(user)))
//			.andExpect(status().isCreated());
	}
}
