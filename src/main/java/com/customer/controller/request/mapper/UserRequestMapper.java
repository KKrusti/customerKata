package com.customer.controller.request.mapper;

import com.customer.controller.request.UserRequest;
import com.customer.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

	User fromRequest(UserRequest userRequest);
}
