package com.customer.data;

import com.customer.controller.request.UserRequest;
import com.customer.controller.response.UserResponse;
import com.customer.domain.Slogan;
import com.customer.domain.User;
import com.customer.entity.SloganEntity;
import com.customer.entity.UserEntity;

public class TestData {

	public static final Long USER_ID = 1L;
	private static final String EMAIL = "shingekinokyojin@manga.com";
	private static final String NAME = "Eren";
	private static final String SURNAME = "Jaegger";
	private static final String STREET = "shiganshima";
	private static final String CITY = "RoseWall";

	public static UserRequest getUserRequest() {
		return UserRequest.builder()
			.name(NAME)
			.surname(SURNAME)
			.street(STREET)
			.city(CITY)
			.email(EMAIL)
			.agreedTermsAndConditions(true)
			.build();
	}

	public static User getUserWithNoId() {
		return User.builder()
			.name(NAME)
			.surname(SURNAME)
			.street(STREET)
			.city(CITY)
			.email(EMAIL)
			.agreedTermsAndConditions(true)
			.build();
	}

	public static UserResponse getUserResponse() {
		return UserResponse.builder()
			.id(1)
			.name(NAME)
			.surname(SURNAME)
			.street(STREET)
			.city(CITY)
			.email(EMAIL)
			.agreedTermsAndConditions(true)
			.build();
	}

	public static User getUser() {
		return getUser(CITY);
	}

	public static User getUser(String city) {
		return User.builder()
			.id(USER_ID)
			.name(NAME)
			.surname(SURNAME)
			.street(STREET)
			.city(city)
			.email(EMAIL)
			.agreedTermsAndConditions(true)
			.build();
	}

	public static UserEntity getUserEntity() {
		return getUserEntity(CITY);
	}

	public static UserEntity getUserEntity(String city) {
		return UserEntity.builder()
			.id(USER_ID)
			.name(NAME)
			.surname(SURNAME)
			.street(STREET)
			.city(city)
			.email(EMAIL)
			.termsAndConditions(true)
			.build();
	}

	public static Slogan getSlogan() {
		return getSlogan(null);
	}

	public static Slogan getSlogan(Long id) {
		return Slogan.builder()
			.id(id)
			.customerId(1L)
			.sloganText("Shinzou sasageyo")
			.build();
	}

	public static SloganEntity getSloganEntity() {
		return SloganEntity.builder()
			.id(1L)
			.customerId(1L)
			.slogan("Shinzou sasageyo")
			.build();
	}
}
