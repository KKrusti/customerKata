package com.customer.data;

import com.customer.domain.Slogan;
import com.customer.domain.User;
import com.customer.entity.SloganEntity;
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
		return getUser("RoseWall");
	}

	public static User getUser(String city) {
		return User.builder()
			.id(USER_ID)
			.name("Eren")
			.surname("Jaegger")
			.street("shiganshima")
			.city(city)
			.email("shingekinokyojin@manga.com")
			.agreedTermsAndConditions(true)
			.build();
	}

	public static UserEntity getUserEntity() {
		return getUserEntity("RoseWall");
	}

	public static UserEntity getUserEntity(String city) {
		return UserEntity.builder()
			.id(USER_ID)
			.name("Eren")
			.surname("Jaegger")
			.street("shiganshima")
			.city(city)
			.email("shingekinokyojin@manga.com")
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
