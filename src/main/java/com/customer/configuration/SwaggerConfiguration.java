package com.customer.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.customer.controller"))
			.paths(PathSelectors.regex("/.*"))
			.build()
			.useDefaultResponseMessages(false)
			.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
			"CustomerKata API",
			"Provides Users and Slogans",
			"1.0.0",
			null,
			new Contact(
				"Carlos Ming",
				"https://www.linkedin.com/in/carlos-ming-6a56b737/",
				"carlesming@hotmail.com"),
			null,
			null,
			Collections.emptyList());
	}
}
