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
public class PasswordEditingController {
	
	private UserEditService userEditService;

	@Autowired
	public PasswordEditingController(UserEditService userEditService) {
		this.userEditService = userEditService;
	}

	@GetMapping("/profile/edit/{username}/password")
	@PreAuthorize("hasAuthority('EDIT_YOUR_PROFILE')")
	public String editPasswordPage(@PathVariable("username") String username, Principal principal, Model model) {
		if (username.equals(principal.getName())) {

			model.addAttribute("passwordEditingForm", new PasswordEditingForm(username));
			return "/user/editing/editpass";	
			
		} else {
			model.addAttribute("error", "You can't edit other people's profiles!");
			return "/message/error";
		}
	}

	@PostMapping("/profile/edit/{username}/password")
	@PreAuthorize("hasAuthority('EDIT_YOUR_PROFILE')")
	public String editPassword(@PathVariable("username") String username, Principal principal, Model model,
		@Valid PasswordEditingForm passwordEditingForm, BindingResult bindingResult) {
		try {

			if (bindingResult.hasErrors()) {
				return "/user/editing/editpass";
			}

			userEditService.editPassword(principal.getName(), username, passwordEditingForm);

			return "redirect:/myprofile";

		} catch (UserNotEditedException e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}
}