package com.application.myapp.config.security;

import com.application.myapp.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class DaoAuthenticationProviderConfig {

	private SecurityService securityService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public DaoAuthenticationProviderConfig(SecurityService securityService, 
		PasswordEncoder passwordEncoder) {

		this.securityService = securityService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean 
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider =
			new DaoAuthenticationProvider();

		authenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationProvider.setUserDetailsService(securityService);

		return authenticationProvider;
	}
}