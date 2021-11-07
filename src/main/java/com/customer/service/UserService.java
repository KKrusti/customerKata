package com.customer.service;

import com.customer.entity.UserEntity;
import com.customer.exceptions.UserUnprocessableException;
import com.customer.model.User;
import com.customer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public User saveOrUpdate(User user){
		var userEntity = saveUser(user);
		var result = userMapper.toDomain(userEntity);
		if(Optional.ofNullable(result).isPresent()){
			return result;
		} else{
			throw new UserUnprocessableException(user.toString());
		}
	}

	private UserEntity saveUser(User user){
		return userRepository.save(userMapper.toEntity(user));
	}

}
