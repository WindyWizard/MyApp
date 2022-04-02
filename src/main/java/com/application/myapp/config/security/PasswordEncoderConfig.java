package com.application.myapp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class PasswordEncoderConfig {

	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}