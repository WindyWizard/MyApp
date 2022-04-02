package com.application.myapp.service.security;

import com.application.myapp.repository.user.UserRepository;
import com.application.myapp.entity.user.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SecurityService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public SecurityService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			UserEntity user = userRepository.findByUsername(username);

			return 
				new org.springframework.security.core.userdetails.User(
				username, 
				user.getPassword(), 
				getAuthorities(user));

		} catch (Exception e) {
			throw new UsernameNotFoundException(
				String.format("UsernameNotFoundException. Cause: %s",
					e.toString()));
		}
	}


	private Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
		return user.getRole()
			.getPermissions()
			.stream()
			.map(
				permission -> new SimpleGrantedAuthority(
					permission.name()))
			.collect(
				Collectors.toList());
	}
}