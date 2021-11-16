package com.customer.controller;

import com.customer.service.SloganService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SloganController.class)
public class FileControllerTest {

	@MockBean
	private SloganService mockSloganService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void withNoMaxSlogans_upload_uploaded() throws Exception {
		MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
			null, "test data".getBytes());
		String response = "File uploaded successfully";

		when(mockSloganService.uploadFile(file)).thenReturn(response);

		mvc.perform(post("/v1/slogan/upload")
			.contentType(MediaType.APPLICATION_JSON)
			.content("File uploaded successfully"))
			.andExpect(status().isOk());
	}
}
