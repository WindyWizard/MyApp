package com.application.myapp.model;

import com.application.myapp.entity.UserEntity;
import com.application.myapp.entity.Role;

import lombok.*;

@Data
public class UserRegistrationForm {

	private String username;
	private String email;
	private String password;
}