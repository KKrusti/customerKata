package com.mango.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface KataRepository<T> {

	T findById(int id);

	T add(T object);

	T update(T object);

}
