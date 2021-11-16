package com.customer.data;

import com.customer.domain.User;
import com.customer.entity.UserEntity;

public class TestData {

	public static final Long USER_ID = 1L;

	public static User getUserWithNoId() {
		return User.builder()
			.name("Eren")
			.surname("Jaegger")
			.street("shiganshima")
			.city("RoseWall")
			.email("shingekinokyojin@manga.com")
			.agreedTermsAndConditions(true)
			.build();
	}

	public static User getUser() {
		return User.builder()
			.id(USER_ID)
			.name("Eren")
			.surname("Jaegger")
			.street("shiganshima")
			.city("RoseWall")
			.email("shingekinokyojin@manga.com")
			.agreedTermsAndConditions(true)
			.build();
	}

	public static UserEntity getUserEntity() {
		return UserEntity.builder()
			.id(USER_ID)
			.name("Eren")
			.surname("Jaegger")
			.street("shiganshima")
			.city("RoseWall")
			.email("shingekinokyojin@manga.com")
			.build();
	}

}
