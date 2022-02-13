package com.customer.controller;

import com.customer.controller.exception.ApiError;
import com.customer.controller.mapper.UserMapper;
import com.customer.controller.request.UserRequest;
import com.customer.controller.response.UserResponse;
import com.customer.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;

	public UserController(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code = 201, message = "User added"),
		@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class),
	})
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest user) {
		var createdUser = userService.saveUser(userMapper.toDomain(user));
		return userMapper.fromDomain(createdUser);
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiResponses({
		@ApiResponse(code = 200, message = "User updated"),
		@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class),
	})
	@PutMapping
	public UserResponse updateUser(@RequestBody UserRequest user) {
		var savedUser = userService.updateUser(user.getId(), userMapper.toDomain(user));
		return userMapper.fromDomain(savedUser);
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiResponses({
		@ApiResponse(code = 200, message = "returns the user that belongs to the id given"),
		@ApiResponse(code = 404, message = "User not found", response = ApiError.class),
		@ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class),
	})
	@GetMapping
	public UserResponse getUser(@RequestParam Long id) {
		var user = userService.getUser(id);
		return userMapper.fromDomain(user);
	}
}
