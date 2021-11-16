package com.customer.useCase.mapper;

import com.customer.entity.UserEntity;
import com.customer.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User toDomain(UserEntity userEntity);

	UserEntity toEntity(User user);

}
