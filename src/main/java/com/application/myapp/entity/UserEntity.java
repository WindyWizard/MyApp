package com.application.myapp.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "User's name cannot be empty")
	@Size(min = 2, max = 15, message = "User's name must be between 2 and 15 characters")
	@Column(name = "username")
	private String username;

	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email must be correct")
	@Size(min = 10, message = "Email must be more than 10 characters")
	@Column(name = "email")
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 6, message = "Password must be more than 6 characters")
	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	public UserEntity() {
		this.role = Role.ROLE_USER;
	}

	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public void encodePassword() {
		this.password = encoder.encode(this.password);
	}
}