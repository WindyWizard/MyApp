package com.application.myapp.service.user;

import com.application.myapp.repository.UserRepository;
import com.application.myapp.entity.UserEntity;

import com.application.myapp.model.User;
import com.application.myapp.model.UserEditingForm;
import com.application.myapp.model.PasswordEditingForm;

import com.application.myapp.exception.UserNotEditedException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserEditService {

	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	private UserRepository userRepository;

	@Autowired
	public UserEditService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void editUser(UserEditingForm userEditingForm) 
		throws UserNotEditedException {

		try {
			UserEntity edited = userRepository.findByUsername(userEditingForm.getUsername());
			edited.setUsername(userEditingForm.getUsername());
			edited.setEmail(userEditingForm.getEmail());
			userRepository.save(edited);

		} catch (Exception e) {
			throw new UserNotEditedException(String.format(
				"Failed to edit user. Details: %s", e.toString()));
		}
	}

	public void editPassword(String executor, String editable, PasswordEditingForm passwordEditingForm) 
		throws UserNotEditedException {

		try {
			UserEntity editableUser = userRepository.findByUsername(editable);

			if (passwordEditingForm.getPassword() != null && passwordEditingForm.getPassword() != "") {
				editableUser.setPassword(encoder.encode(passwordEditingForm.getPassword()));
			}

			userRepository.save(editableUser);

		} catch (Exception e) {
			throw new UserNotEditedException(String.format(
				"Failed to update user. Details: %s", e.toString()));
		}
	}
}