package com.application.myapp.config.security;

import com.application.myapp.entity.user.Permission;
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

				.antMatchers(HttpMethod.GET, "/myprofile")
					.hasAuthority(Permission.READ_ANY_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profiles")
					.hasAuthority(Permission.READ_ANY_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/{username}")
					.hasAuthority(Permission.READ_ANY_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/edit/{username}")
					.hasAuthority(Permission.UPDATE_YOUR_PROFILE.name())

				.antMatchers(HttpMethod.POST, "/profile/edit/{username}")
					.hasAuthority(Permission.UPDATE_YOUR_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/edit/{username}/password")
					.hasAuthority(Permission.UPDATE_YOUR_PROFILE.name())

				.antMatchers(HttpMethod.POST, "/profile/edit/{username}/password")
					.hasAuthority(Permission.UPDATE_YOUR_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/edit_as_admin/{username}")
					.hasAuthority(Permission.UPDATE_ANY_PROFILE.name())

				.antMatchers(HttpMethod.POST, "/profile/edit_as_admin/{username}")
					.hasAuthority(Permission.UPDATE_ANY_PROFILE.name())

				.antMatchers(HttpMethod.POST, "/profile/delete/{username}")
					.hasAuthority(Permission.DELETE_ANY_PROFILE.name())

				.antMatchers(HttpMethod.GET, "/profile/edit_user_rights/{username}")
					.hasAuthority(Permission.EDIT_USER_RIGHTS.name())

				.antMatchers(HttpMethod.POST, "/profile/edit_user_rights/{username}")
					.hasAuthority(Permission.EDIT_USER_RIGHTS.name())

			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/myprofile")
			.and()
				.logout()
					.logoutSuccessUrl("/home")
					.deleteCookies("JSESSIONID")
			.and()
				.csrf().disable();
	}
}