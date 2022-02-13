package com.customer.controller.mapper;

import com.customer.controller.request.UserRequest;
import com.customer.controller.response.UserResponse;
import com.customer.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User toDomain(UserRequest userRequest);

	UserResponse fromDomain(User user);
}
