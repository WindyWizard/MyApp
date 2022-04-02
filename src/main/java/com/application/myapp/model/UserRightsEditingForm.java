package com.application.myapp.model;

import com.application.myapp.entity.UserEntity;

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