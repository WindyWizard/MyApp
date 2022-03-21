package com.application.myapp.config;

import com.application.myapp.entity.Permission;
import com.application.myapp.service.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private SecurityService securityService;

	@Autowired
	public SecurityConfig(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/home")
					.permitAll()

				.antMatchers(HttpMethod.GET, "/registration")
					.permitAll()

				.antMatchers(HttpMethod.POST, "/registration")
					.permitAll()

				.antMatchers(HttpMethod.GET, "/my_profile")
					.hasAuthority(Permission.READ_ANY_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/all_users")
					.hasAuthority(Permission.READ_ANY_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/{username}")
					.hasAuthority(Permission.READ_ANY_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/edit/{username}")
					.hasAuthority(Permission.UPDATE_YOUR_PROFILE.name())

				.antMatchers(HttpMethod.PATCH, "/profile/edit/{username}")
					.hasAuthority(Permission.UPDATE_YOUR_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/edit_as_admin/{username}")
					.hasAuthority(Permission.UPDATE_ANY_PROFILE.name())

				.antMatchers(HttpMethod.PATCH, "/profile/edit_as_admin/{username}")
					.hasAuthority(Permission.UPDATE_ANY_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/delete/{username}")
					.hasAuthority(Permission.DELETE_ANY_PROFILE.name())

				.antMatchers(HttpMethod.DELETE, "/profile/delete/{username}")
					.hasAuthority(Permission.DELETE_ANY_PROFILE.name())
			.and()
				.httpBasic()
			.and()
				.logout()
					.logoutSuccessUrl("/home")
			.and()
				.csrf().disable();
	}

	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean 
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider =
			new DaoAuthenticationProvider();

		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(securityService);

		return authenticationProvider;
	}
}