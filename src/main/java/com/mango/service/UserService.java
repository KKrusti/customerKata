package com.mango.service;

import com.mango.model.User;
import com.mango.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User create(User user){
		return userRepository.add(user);
	}

	public User update(User user){
		return userRepository.update(user);
	}

}
