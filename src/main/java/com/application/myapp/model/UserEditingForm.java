package com.application.myapp.model;

import com.application.myapp.entity.UserEntity;
import com.application.myapp.entity.Role;

import lombok.*;
import javax.validation.constraints.*;

@Data
public class UserEditingForm {

	@NotEmpty(message = "User's name cannot be empty")
	@Size(min = 2, max = 15, message = "User's name must be between 2 and 15 characters")
	private String username;

	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email must be correct")
	@Size(min = 10, message = "Email must be more than 10 characters")
	private String email;

	public UserEditingForm(String username, String email) {
		this.username = username;
		this.email = email;
	}
}