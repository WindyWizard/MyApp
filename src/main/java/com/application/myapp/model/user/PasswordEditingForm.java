package com.application.myapp.model.user;

import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.entity.user.Role;
import lombok.*;
import javax.validation.constraints.*;

@Data
public class PasswordEditingForm {

	private String username;
	
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 6, message = "Password must be more than 6 characters")
	private String password;

	public PasswordEditingForm(String username) {
		this.username = username;
	}
}