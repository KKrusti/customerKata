package com.customer.data;

import com.customer.entity.UserEntity;
import com.customer.model.User;

public class TestData {

	private static final Long USER_ID = 1L;

	public static User getUser() {
	return User.builder()
			.id(USER_ID)
			.name("Eren")
			.surname("Jaegger")
			.street("shiganshima")
			.city("RoseWall")
			.email("shingekinokyojin@manga.com")
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
