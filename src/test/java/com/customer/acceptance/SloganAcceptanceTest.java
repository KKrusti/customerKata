package com.customer.acceptance;

import com.customer.controller.request.SloganRequest;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.annotation.PostConstruct;

import static com.customer.helpers.FileUtils.getFileContents;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"h2DataSource", "local"})
@Sql({
	"data/schema.sql",
})
class SloganAcceptanceTest {

	@LocalServerPort
	private int port;
	private String addSloganEndpoint;

	@PostConstruct
	public void init() {
		addSloganEndpoint = "http://localhost:" + port + "/v1/slogan/upload";
	}

	@Test
	@Sql(scripts = "/data/schema.sql")
	void should_add_a_slogan() throws JSONException {
		var request = addSloganRequest();

		Response response =
			given()
				.contentType(APPLICATION_JSON_VALUE)
				.body(request)
				.when()
				.post(addSloganEndpoint)
				.then()
				.statusCode(CREATED.value())
				.extract()
				.response();

		assertNotNull(response);
		JSONAssert.assertEquals(
			getFileContents("data/acceptance/expectedSloganResponse.json"),
			response.body().print(),
			false);

	}

	@Test
	@SqlGroup({
		@Sql("/data/schema.sql"),
		@Sql("/data/insert_3_slogans_for_same_user.sql"),
	})
	void should_return_max_slogans_reached_exception_after_uploading_fourth_slogan() {
		var request = addSloganRequest();

		Response response =
			given()
				.contentType(APPLICATION_JSON_VALUE)
				.body(request)
				.when()
				.post(addSloganEndpoint)
				.then()
				.statusCode(FORBIDDEN.value())
				.extract()
				.response();

		assertNotNull(response);
	}

	private SloganRequest addSloganRequest() {
		return SloganRequest.builder()
			.customerId("1")
			.text("Kamado Tanjiro")
			.build();
	}

}
