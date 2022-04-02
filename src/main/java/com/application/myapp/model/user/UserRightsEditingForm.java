package com.application.myapp.model.user;

import com.application.myapp.entity.user.UserEntity;
import lombok.*;
import javax.validation.constraints.*;

@Data
public class UserRightsEditingForm {

	private String username;
	private String role;

	public UserRightsEditingForm(String username) {
		this.username = username;
		this.role = "ROLE_USER";
	}
}