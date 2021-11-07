package com.customer.service;

import com.customer.entity.UserEntity;
import com.customer.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User toDomain(UserEntity userEntity);

	UserEntity toEntity(User user);

}
