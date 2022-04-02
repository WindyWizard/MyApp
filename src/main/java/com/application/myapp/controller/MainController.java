package com.application.myapp.controller;

import com.application.myapp.service.user.UserEditService;
import com.application.myapp.service.user.UserGetService;
import com.application.myapp.model.user.User;
import com.application.myapp.entity.user.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	@GetMapping("/home")
	public String homepage() {
		return "/home";
	}

	@GetMapping("/login")
	public String loginPage(@ModelAttribute UserEntity userEntity) {
		return "/user/auth/login";
	}
}