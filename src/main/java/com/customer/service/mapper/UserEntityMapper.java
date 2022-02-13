package com.customer.service.mapper;

import com.customer.domain.User;
import com.customer.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

	@Mapping(target = "agreedTermsAndConditions", source = "termsAndConditions")
	User toDomain(UserEntity userEntity);

	@Mapping(target = "termsAndConditions", source = "agreedTermsAndConditions")
	UserEntity toEntity(User user);

}
