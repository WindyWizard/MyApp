package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserEditService;
import com.application.myapp.service.user.UserGetService;

import com.application.myapp.model.User;
import com.application.myapp.model.PasswordEditingForm;
import com.application.myapp.model.UserEditingForm;

import com.application.myapp.entity.UserEntity;
import com.application.myapp.exception.*;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;
import javax.validation.Valid;

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

	// EDIT USER

	@GetMapping("/profile/edit/{username}")
	public String editProfilePage(@PathVariable("username") String username, Principal principal, Model model) {
		if (username.equals(principal.getName())) {

			try {
				model.addAttribute("userEditingForm", 
					new UserEditingForm(username, userGetService.getUserByUsername(username).getEmail()));
				return "/user/editing/edit";	

			} catch (Exception e) {
				return "/message/error";
			}
			
			
		} else {

			return "/message/error";
		}
	}

	@PostMapping("/profile/edit/{username}")
	public String editUser(@PathVariable("username") String username, 
		@Valid UserEditingForm userEditingForm, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return "/message/error";
			}

			userEditService.editUser(userEditingForm);

			return "redirect:/myprofile";

		} catch (UserNotEditedException e) {
			return "/message/error";
		}
	}

	// EDIT USER PASSWORD

	@GetMapping("/profile/edit/{username}/password")
	public String editPasswordPage(@PathVariable("username") String username, Principal principal, Model model) {
		if (username.equals(principal.getName())) {

			model.addAttribute("passwordEditingForm", new PasswordEditingForm(username));
			return "/user/editing/editpass";	
			
		} else {

			return "/message/error";
		}
	}

	@PostMapping("/profile/edit/{username}/password")
	public String editPassword(@PathVariable("username") String username, Principal principal,
		@Valid PasswordEditingForm passwordEditingForm, BindingResult bindingResult) {
		try {

			if (bindingResult.hasErrors()) {
				return "/user/editing/editpass";
			}

			userEditService.editPassword(principal.getName(), username, passwordEditingForm);

			return "redirect:/myprofile";

		} catch (UserNotEditedException e) {
			return "/message/error";
		}
	}

	// EDIT USER AS ADMIN

	@GetMapping("/profile/edit_as_admin/{username}")
	public String editUserAsAdmin_Page(@PathVariable("username") String username, Principal principal, Model model) {
		try {
			model.addAttribute("userEditingForm", 
				new UserEditingForm(username, userGetService.getUserByUsername(username).getEmail()));
			model.addAttribute("executor", userGetService.getUserEntityByUsername(principal.getName()));
			return "/user/editing/edit_as_admin";	

		} catch (Exception e) {
			return "/message/error";
		}
	}

	@PostMapping("/profile/edit_as_admin/{username}")
	public String editUserAsAdmin(@PathVariable("username") String username,
		@Valid UserEditingForm userEditingForm, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return "/message/error";
			}

			userEditService.editUser(userEditingForm);

			return "redirect:/profiles";

		} catch (UserNotEditedException e) {
			return "/message/error";
		}
	}
}