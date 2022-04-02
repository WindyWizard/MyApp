package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserRightsService;
import com.application.myapp.service.user.UserGetService;

import com.application.myapp.model.User;
import com.application.myapp.model.UserRightsEditingForm;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.exception.*;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@Controller
public class UserRightsController {
	
	private UserGetService userGetService;
	private UserRightsService userRightsService;

	@Autowired
	public UserRightsController(UserGetService userGetService, UserRightsService userRightsService) {
		this.userGetService = userGetService;
		this.userRightsService = userRightsService;
	}

	@GetMapping("/profile/edit_user_rights/{username}")
	public String editUserRights_Page(@PathVariable("username") String username, Principal principal, Model model) {
		try {
			if (!username.equals(principal.getName())) {

				if (userGetService.getUserByUsername(username) != null) {

					model.addAttribute("userRightsEditingForm", new UserRightsEditingForm(username));

					return "/user/editing/edit_user_rights";

				} else {
					return "/message/error";
				}

			} else {
				return "/message/error";
			}

		} catch (Exception e) {
			return "/message/error";
		}
		
	}

	@PostMapping("/profile/edit_user_rights/{username}")
	public String editUserRights(@PathVariable("username") String username, UserRightsEditingForm userRightsEditingForm) {
		try {
			userRightsService.editUserRights(username, userRightsEditingForm);

			return "redirect:/profiles";

		} catch (Exception e) {
			return "/message/error";
		}
	}
}