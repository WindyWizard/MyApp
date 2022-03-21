package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserEditService;
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
public class UserEditController {
	
	private UserEditService userService;

	@Autowired
	public UserEditController(UserEditService userService) {
		this.userService = userService;
	}

	@PatchMapping("/profile/edit/{username}")
	public String editUser(Principal principal, @PathVariable("username") String username,
		@RequestBody UserEditForm userEditForm) {
		try {

			if (username.equals(principal.getName())) {
				userService.editUser(principal.getName(), username, userEditForm);

				return "User successfuly edited!";

			} else {

				return "You cannot edit another user!";
			}

		} catch (UserNotUpdatedException e) {
			return e.getMessage();
		}
	}

	@PatchMapping("/profile/edit_as_admin/{username}")
	public String editUserAsAdmin(Principal principal, @PathVariable("username") String username,
		@RequestBody UserEditForm userEditForm) {
		try {
			userService.editUserAsAdmin(principal.getName(), username, userEditForm);

			return "User successfuly edited!";

		} catch (UserNotUpdatedException e) {
			return e.getMessage();
		}
	}
}