package com.application.myapp.model.user;

import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.entity.user.Role;
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