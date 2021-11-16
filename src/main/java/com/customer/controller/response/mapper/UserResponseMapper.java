package com.customer.controller.response.mapper;

import com.customer.controller.response.UserResponse;
import com.customer.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

	UserResponse fromDomain(User user);
}
