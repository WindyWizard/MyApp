package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserRegistrationService;
import com.application.myapp.model.User;;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.exception.*;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;
import javax.validation.Valid;

import java.util.List;
import java.security.Principal;

@Controller
public class UserRegistrationController {
	
	private UserRegistrationService userService;

	@Autowired
	public UserRegistrationController(UserRegistrationService userService) {
		this.userService = userService;
	}

	@GetMapping("/registration")
	public String registrationPage(@ModelAttribute UserEntity userEntity) {
		return "/user/auth/registration";
	}

	@PostMapping("/registration")
	public String registerUser(@Valid UserEntity userEntity, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return "/user/auth/registration";
			}

			userService.registerUser(userEntity);

			return "redirect:/login";

		} catch (UserNotRegisteredException e) {
			return e.getMessage();
		}
	}
}
