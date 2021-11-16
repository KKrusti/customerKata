package com.customer.controller;

import com.customer.controller.request.UserRequest;
import com.customer.controller.request.mapper.UserRequestMapper;
import com.customer.controller.response.UserResponse;
import com.customer.controller.response.mapper.UserResponseMapper;
import com.customer.domain.User;
import com.customer.useCase.AddUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private final AddUser addUser;
	private final UserRequestMapper userRequestMapper;
	private final UserResponseMapper userResponseMapper;

	public UserController(AddUser addUser, UserRequestMapper userRequestMapper, UserResponseMapper userResponseMapper) {
		this.addUser = addUser;
		this.userRequestMapper = userRequestMapper;
		this.userResponseMapper = userResponseMapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse createUser(@RequestBody UserRequest userRequest){
		var savedUser = saveUser(userRequest);
		return userResponseMapper.fromDomain(savedUser);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public UserResponse updateUser(@RequestBody UserRequest userRequest) {
		var updatedUser = saveUser(userRequest);
		return userResponseMapper.fromDomain(updatedUser);
	}

	private User saveUser(UserRequest userRequest){
		var user = userRequestMapper.fromRequest(userRequest);
		return addUser.addFrom(user);
	}
}
