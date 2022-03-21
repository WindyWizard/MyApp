package com.application.myapp.model;

import com.application.myapp.entity.UserEntity;
import com.application.myapp.entity.Role;

import lombok.*;

@Data
public class User {

	private String username;
	private String email;

	public static User toModel(UserEntity userEntity) {
		User user = new User();

		user.setUsername(userEntity.getUsername());
		user.setEmail(userEntity.getEmail());

		return user;
	}
}