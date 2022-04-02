package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserDeleteService;
import com.application.myapp.model.user.User;
import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.exception.user.UserNotDeletedException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class UserDeleteController {
	
	private UserDeleteService userService;

	@Autowired
	public UserDeleteController(UserDeleteService userService) {
		this.userService = userService;
	}

	@PostMapping("/profile/delete/{username}")
	public String deleteUser(@PathVariable("username") String username, Model model) {
		try {
			userService.deleteUser(username);

			return "redirect:/profiles";

		} catch (UserNotDeletedException e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}
}