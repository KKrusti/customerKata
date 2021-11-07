package com.customer.service;

import com.customer.model.User;
import com.customer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User create(User user){
		return userRepository.add(user);
	}

	public User update(User user){
		return userRepository.update(user);
	}

}
