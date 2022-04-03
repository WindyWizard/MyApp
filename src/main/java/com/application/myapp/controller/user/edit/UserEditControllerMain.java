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
public class UserEditControllerMain {
	
	private UserEditService userEditService;
	private UserGetService userGetService;

	@Autowired
	public UserEditControllerMain(UserEditService userEditService, UserGetService userGetService) {
		this.userEditService = userEditService;
		this.userGetService = userGetService;
	}

	@GetMapping("/profile/edit/{username}")
	@PreAuthorize("hasAuthority('EDIT_YOUR_PROFILE')")
	public String editProfilePage(@PathVariable("username") String username, Principal principal, Model model) {
		if (username.equals(principal.getName())) {

			try {
				model.addAttribute("userEditingForm", 
					new UserEditingForm(username, userGetService.getUserByUsername(username).getEmail()));
				return "/user/editing/edit";	

			} catch (Exception e) {
				model.addAttribute("error", e);
				return "/message/error";
			}
			
		} else {
			model.addAttribute("error", "You can't edit other people's profiles!");
			return "/message/error";
		}
	}

	@PostMapping("/profile/edit/{username}")
	@PreAuthorize("hasAuthority('EDIT_YOUR_PROFILE')")
	public String editUser(@PathVariable("username") String username, Model model,
		@Valid UserEditingForm userEditingForm, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return "/message/error";
			}

			userEditService.editUser(username, userEditingForm);

			return "redirect:/logout";

		} catch (UserNotEditedException e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}
}