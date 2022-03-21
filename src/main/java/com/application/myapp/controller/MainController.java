package com.application.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	
	@GetMapping("/home")
	public String homepage() {
		return "/home";
	}

	@GetMapping("/registration")
	public String registrationPage() {
		return "/registration";
	}

	@GetMapping("/my_profile")
	public String myProfilePage() {
		return "/my_profile";
	}

	@GetMapping("/all_users")
	public String allUsersPage() {
		return "/all_users";
	}

	@GetMapping("/profile/{username}")
	public String profilePage(@PathVariable("username") String username) {
		return "/profile";
	}

	@GetMapping("/profile/edit/{username}")
	public String editProfilePage(@PathVariable("username") String username) {
		return "/edit_profile";
	}

	@GetMapping("/profile/edit_as_admin/{username}")
	public String editProfilePageAsAdmin(@PathVariable("username") String username) {
		return "/edit_profile";
	}

	@GetMapping("/profile/delete/{username}")
	public String deleteProfilePage(@PathVariable("username") String username) {
		return "/delete_profile";
	}
}