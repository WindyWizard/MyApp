package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserRegistrationService;
import com.application.myapp.model.user.User;;
import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.exception.user.UserNotRegisteredException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import java.security.Principal;
import org.springframework.ui.Model;

@Controller
public class UserRegistrationController {
	
	private UserRegistrationService userRegistrationService;

	@Autowired
	public UserRegistrationController(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
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

			userRegistrationService.registerUser(userEntity);

			return "redirect:/login";

		} catch (UserNotRegisteredException e) {
			return e.getMessage();
		}
	}
}
