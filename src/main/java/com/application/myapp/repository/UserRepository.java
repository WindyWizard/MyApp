package com.application.myapp.repository;

import com.application.myapp.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
	void deleteByUsername(String username);
}