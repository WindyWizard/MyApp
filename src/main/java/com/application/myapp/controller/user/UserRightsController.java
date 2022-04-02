package com.application.myapp.controller.user;

import com.application.myapp.service.user.UserRightsService;
import com.application.myapp.service.user.UserGetService;
import com.application.myapp.model.user.User;
import com.application.myapp.model.user.UserRightsEditingForm;
import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.exception.user.UserRightsEditedException;
import com.application.myapp.exception.user.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
					model.addAttribute("error", "User not found!");
					return "/message/error";
				}

			} else {
				model.addAttribute("error", "You can't change your permissions	");
				return "/message/error";
			}

		} catch (UserNotFoundException e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
		
	}

	@PostMapping("/profile/edit_user_rights/{username}")
	public String editUserRights(@PathVariable("username") String username, Model model, 
		UserRightsEditingForm userRightsEditingForm) {
		try {
			userRightsService.editUserRights(username, userRightsEditingForm);

			return "redirect:/profiles";

		} catch (UserRightsEditedException e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}
}