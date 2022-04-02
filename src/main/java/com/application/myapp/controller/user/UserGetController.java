package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserGetService;
import com.application.myapp.model.user.User;
import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.exception.user.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.security.Principal;

@Controller
public class UserGetController {
	
	private UserGetService userService;

	@Autowired
	public UserGetController(UserGetService userService) {
		this.userService = userService;
	}

	@GetMapping("/profiles")
	public String getOtherUsers(Principal principal, Model model) {
		model.addAttribute("users",  userService.getOtherUsers(principal.getName()));
		return "/user/profile/all";
	}

	@GetMapping("/profile/{username}")
	public String getProfileByUsername(@PathVariable("username") String username, Principal principal, Model model) throws UserNotFoundException {
		model.addAttribute("user", userService.getUserByUsername(username));
		model.addAttribute("executor", userService.getUserEntityByUsername(principal.getName()));
		return "/user/profile/selected";
	}

	@GetMapping("/myprofile")
	public String getYourProfile(Principal principal, Model model) throws UserNotFoundException {
		try {
			model.addAttribute("user", userService.getUserByUsername(principal.getName()));
			return "/user/profile/my";

		} catch (UserNotFoundException e) {
			model.addAttribute("error", e);	
			return "/message/error";
		}
		
	}
}