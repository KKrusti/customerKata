package com.customer.service;

import com.customer.entity.UserEntity;
import com.customer.exceptions.TermsAndConditionsNotAcceptedException;
import com.customer.exceptions.UserNotFoundException;
import com.customer.exceptions.UserUnprocessableException;
import com.customer.domain.User;
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

	public User getUser(Long userId){
		return userRepository.findById(userId)
			.map(userMapper::toDomain)
			.orElseThrow(() -> new UserNotFoundException(userId));
	}

	public User saveUser(User user){
		checkTermsAndConditions(user);
		var userEntity = save(user);
		var result = userMapper.toDomain(userEntity);
		if(Optional.ofNullable(result).isPresent()){
			return result;
		} else{
			throw new UserUnprocessableException(user.toString());
		}
	}

	private void checkTermsAndConditions(User user){
		if(!user.isAgreedTermsAndConditions()){
			throw new TermsAndConditionsNotAcceptedException();
		}
	}

	private UserEntity save(User user){
		return userRepository.save(userMapper.toEntity(user));
	}

}
