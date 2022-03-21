package com.application.myapp.entity;

import com.application.myapp.model.UserRegistrationForm;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public UserEntity() {
	
	}

	public UserEntity(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = encoder.encode(password);
		this.role = Role.ROLE_USER;
	}

	public static UserEntity toEntity(UserRegistrationForm userRegistrationForm) {
		UserEntity userEntity = new UserEntity();

		userEntity.setUsername(userRegistrationForm.getUsername());
		userEntity.setEmail(userRegistrationForm.getEmail());
		userEntity.setPassword(encoder.encode(userRegistrationForm.getPassword()));
		userEntity.setRole(Role.ROLE_USER);

		return userEntity;
	}
}