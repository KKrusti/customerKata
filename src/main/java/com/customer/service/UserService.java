package com.customer.service;

import com.customer.domain.User;
import com.customer.entity.UserEntity;
import com.customer.exceptions.TermsAndConditionsNotAcceptedException;
import com.customer.exceptions.UserNotFoundException;
import com.customer.repository.UserRepository;
import com.customer.service.mapper.UserEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserEntityMapper userEntityMapper;

	public UserService(UserRepository userRepository, UserEntityMapper userEntityMapper) {
		this.userRepository = userRepository;
		this.userEntityMapper = userEntityMapper;
	}

	public User getUser(Long userId) {
		return this.userRepository.findById(userId)
			.map(userEntityMapper::toDomain)
			.orElseThrow(() -> new UserNotFoundException(userId));
	}

	public User saveUser(User user) {
		checkTermsAndConditions(user);
		return userEntityMapper.toDomain(
			userRepository.save(userEntityMapper.toEntity(user)));
	}

	public User updateUser(Long userId, User user) {
		return userRepository.findById(userId)
			.map(userEntity -> {
				updateUserFromEntity(userEntity, user);
				return userEntityMapper.toDomain(userRepository.save(userEntity));
			})
			.orElseThrow(
				() -> new UserNotFoundException(userId));
	}

	private void updateUserFromEntity(UserEntity userEntity, User user) {
		userEntity.setName(user.getName());
		userEntity.setSurname(user.getSurname());
		userEntity.setStreet(user.getStreet());
		userEntity.setCity(user.getCity());
		userEntity.setEmail(user.getEmail());
	}

	private void checkTermsAndConditions(User user) {
		if (!user.isAgreedTermsAndConditions()) {
			throw new TermsAndConditionsNotAcceptedException();
		}
	}
}
