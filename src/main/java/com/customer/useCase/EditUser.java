package com.customer.useCase;

import com.customer.entity.UserEntity;
import com.customer.domain.User;
import com.customer.repository.UserRepository;
import com.customer.useCase.exception.UserNotFoundException;
import com.customer.useCase.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class EditUser {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public EditUser(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public User updateUser(User user){
		return userRepository
			.findById(user.getId())
			.map(
				userEntity -> {
					updateUserFrom(userEntity, user);
					return userMapper.toDomain(userRepository.save(userEntity));
				})
			.orElseThrow(
				() -> new UserNotFoundException(user.getId()));
	}

	private void updateUserFrom(UserEntity userEntity, User user) {
		userEntity.setName(user.getName());
		userEntity.setSurname(user.getSurname());
		userEntity.setStreet(user.getStreet());
		userEntity.setCity(user.getCity());
		userEntity.setEmail(user.getEmail());
	}

}
