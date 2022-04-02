package com.application.myapp.repository.user;

import com.application.myapp.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
	void deleteByUsername(String username);
}