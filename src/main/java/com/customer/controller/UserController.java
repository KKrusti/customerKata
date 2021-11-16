package com.customer.controller;

import com.customer.domain.User;
import com.customer.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		var updatedUser = userService.saveUser(user);
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping
	public ResponseEntity<User> getUser(@RequestBody Long id){
		var user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}
}
