package com.customer.useCase;

import com.customer.domain.User;
import com.customer.repository.UserRepository;
import com.customer.useCase.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class AddUser {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public AddUser(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public User addFrom(User user){
		var userEntity = userMapper.toEntity(user);
		return userMapper.toDomain(userRepository.save(userEntity));
	}

}
