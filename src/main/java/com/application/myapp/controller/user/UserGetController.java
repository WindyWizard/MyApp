package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserGetService;
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
public class UserGetController {
	
	private UserGetService userService;

	@Autowired
	public UserGetController(UserGetService userService) {
		this.userService = userService;
	}

	// @GetMapping("/all_users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// @GetMapping("/profile/{username}")
	public User getProfileByUsername(@PathVariable("username") String username) throws UserNotFoundException {
		return userService.getUserByUsername(username);
	}

	// @GetMapping("/my_profile")
	public User getYourProfile(Principal principal) throws UserNotFoundException {
		return userService.getUserByUsername(principal.getName());
	}
}