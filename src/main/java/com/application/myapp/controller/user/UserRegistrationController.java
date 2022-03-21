package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserRegistrationService;
import com.application.myapp.model.User;
import com.application.myapp.model.UserRegistrationForm;
import com.application.myapp.model.UserEditForm;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@RestController
public class UserRegistrationController {
	
	private UserRegistrationService userService;

	@Autowired
	public UserRegistrationController(UserRegistrationService userService) {
		this.userService = userService;
	}

	@PostMapping("/registration")
	public String registerUser(@RequestBody UserRegistrationForm userRegistrationForm) {
		try {
			userService.registerUser(userRegistrationForm);

			return "User successfully registered!";

		} catch (UserNotCreatedException e) {
			return e.getMessage();
		}
	}
}