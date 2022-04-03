package com.application.myapp.controller.user.edit;

import com.application.myapp.service.user.UserEditService;
import com.application.myapp.service.user.UserGetService;
import com.application.myapp.model.user.User;
import com.application.myapp.model.user.PasswordEditingForm;
import com.application.myapp.model.user.UserEditingForm;
import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.exception.user.UserNotEditedException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.ui.Model;
import java.security.Principal;

@Controller
public class EditUserAsAdminController {
	
	private UserEditService userEditService;
	private UserGetService userGetService;

	@Autowired
	public EditUserAsAdminController(UserEditService userEditService, UserGetService userGetService) {
		this.userEditService = userEditService;
		this.userGetService = userGetService;
	}
@GetMapping("/profile/edit_as_admin/{username}")
	@PreAuthorize("hasAuthority('EDIT_ANY_PROFILE')")
	public String editUserAsAdmin_Page(@PathVariable("username") String username, Principal principal, Model model) {
		try {
			model.addAttribute("userEditingForm", 
				new UserEditingForm(username, userGetService.getUserByUsername(username).getEmail()));
			model.addAttribute("executor", userGetService.getUserEntityByUsername(principal.getName()));
			return "/user/editing/edit_as_admin";	

		} catch (Exception e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}

	@PostMapping("/profile/edit_as_admin/{username}")
	@PreAuthorize("hasAuthority('EDIT_ANY_PROFILE')")
	public String editUserAsAdmin(@PathVariable("username") String username, Model model,
		@Valid UserEditingForm userEditingForm, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("error", "You entered incorrect data for editing!");
				return "/message/error";
			}

			userEditService.editUser(username, userEditingForm);
			return "redirect:/profiles";

		} catch (UserNotEditedException e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}
}