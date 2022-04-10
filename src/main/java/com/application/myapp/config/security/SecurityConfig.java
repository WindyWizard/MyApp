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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;  

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private SecurityService securityService;

	@Autowired
	public SecurityConfig(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				// HOME

				.antMatchers(HttpMethod.GET, "/home")
					.permitAll()
				.antMatchers(HttpMethod.POST, "/image/upload")
					.permitAll()

				// REGISTRATION

				.antMatchers(HttpMethod.GET, "/registration")
					.permitAll()

				.antMatchers(HttpMethod.POST, "/registration")
					.permitAll()
					
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