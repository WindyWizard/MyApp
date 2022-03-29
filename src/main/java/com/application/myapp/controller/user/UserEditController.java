package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserEditService;
import com.application.myapp.service.user.UserGetService;
import com.application.myapp.model.User;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.exception.*;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

import java.util.List;
import java.security.Principal;

@Controller
public class UserEditController {
	
	private UserEditService userEditService;
	private UserGetService userGetService;

	@Autowired
	public UserEditController(UserEditService userEditService, UserGetService userGetService) {
		this.userEditService = userEditService;
		this.userGetService = userGetService;
	}

	@GetMapping("/profile/edit/{username}")
	public String editProfilePage(@PathVariable("username") String username, Model model) {
		try {
			model.addAttribute("userEntity", userGetService.getUserByUsername(username));
		} catch (Exception e) {
			return "redirect:/myprofile";
		}

		return "/user/editing/edit";
	}

	@PostMapping("/profile/edit/{username}")
	public String editUser(@PathVariable("username") String username, Principal principal, 
		UserEntity userEntity) {
		try {
			if (username.equals(principal.getName())) {
				userEditService.editUser(userEntity);

				return "redirect:/myprofile";
				
			} else {

				return "redirect:/error";
			}

		} catch (UserNotEditedException e) {
			return "redirect:/error";
		}
	}

	// @GetMapping("/profile/edit_as_admin/{username}")
	// public String editProfilePageAsAdmin(@PathVariable("username") String username, Model model) {
	// 	try {
	// 		User user = userGetService.getUserByUsername(username);
	// 		model.addAttribute("user", user);
	// 		model.addAttribute("userEditForm", new UserEditForm(
	// 			user.getUsername(),
	// 			user.getEmail()));
	// 	} catch (Exception e) {
	// 		return "redirect:/myprofile";
	// 	}

	// 	return "/user/editing/edit";
	// }

	// @PostMapping("/profile/edit_as_admin/{username}")
	// public String editUserAsAdmin(Principal principal, @PathVariable("username") String username,
	// 	UserEditForm userEditForm) {
	// 	try {
	// 		userEditService.editUserAsAdmin(principal.getName(), username, userEditForm);

	// 		return "redirect:/myprofile";

	// 	} catch (UserNotUpdatedException e) {
	// 		return e.getMessage();
	// 	}
	// }
}