package com.customer.service;

import com.customer.domain.User;
import com.customer.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "agreedTermsAndConditions", constant = "true")
	User toDomain(UserEntity userEntity);

	UserEntity toEntity(User user);

}
