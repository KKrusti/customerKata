package com.mango.repository;

import com.mango.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository implements KataRepository<User> {

	private Map<Integer, User> storedUser = new HashMap<>();

	@Override
	public User findById(int id) {
		return storedUser.get(id);
	}

	@Override
	public User add(User user) {
		//dummy method to store somewhere, it could be inmemory ddbb or anywhere.
		storedUser.put(user.getId(), user);
		return user;
	}

	@Override
	public User update(User user) {
		//since dummy database is a hashmap the code is the same as add, but if it was a real database we could
		//have a createOrUpdate method (same for both needs) or two separated methods with different logic.
		storedUser.put(user.getId(), user);
		return user;
	}
}
