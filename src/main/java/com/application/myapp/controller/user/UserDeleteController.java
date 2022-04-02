package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserDeleteService;
import com.application.myapp.model.User;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.exception.*;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@Controller
public class UserDeleteController {
	
	private UserDeleteService userService;

	@Autowired
	public UserDeleteController(UserDeleteService userService) {
		this.userService = userService;
	}

	@PostMapping("/profile/delete/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		try {
			userService.deleteUser(username);

			return "redirect:/profiles";

		} catch (UserNotDeletedException e) {
			return "/message/error";
		}
	}
}