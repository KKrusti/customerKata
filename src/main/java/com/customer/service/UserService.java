package com.customer.service;

import com.customer.domain.User;
import com.customer.entity.UserEntity;
import com.customer.exceptions.TermsAndConditionsNotAcceptedException;
import com.customer.exceptions.UserNotFoundException;
import com.customer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public User getUser(Long userId) {
		return this.userRepository.findById(userId)
			.map(userMapper::toDomain)
			.orElseThrow(() -> new UserNotFoundException(userId));
	}

	public User saveUser(User user) {
		checkTermsAndConditions(user);
		return userMapper.toDomain(save(user));
	}

	public User updateUser(User user) {
		return userMapper.toDomain(save(user));
	}

	private void checkTermsAndConditions(User user) {
		if (!user.isAgreedTermsAndConditions()) {
			throw new TermsAndConditionsNotAcceptedException();
		}
	}

	private UserEntity save(User user) {
		return userRepository.save(userMapper.toEntity(user));
	}

}
