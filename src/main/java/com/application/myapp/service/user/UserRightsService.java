package com.application.myapp.service.user;

import com.application.myapp.model.UserRightsEditingForm;
import com.application.myapp.repository.UserRepository;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.model.User;
import com.application.myapp.entity.Role;

import com.application.myapp.exception.UserNotDeletedException;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserRightsService {

	private UserRepository userRepository;

	@Autowired
	public UserRightsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void editUserRights(String username, UserRightsEditingForm userRightsEditingForm) {
		UserEntity user = userRepository.findByUsername(username);

		if (userRightsEditingForm.getRole().equals("ROLE_ADMIN")) {
			user.setRole(Role.ROLE_ADMIN);

			userRepository.save(user);
		} else {
			user.setRole(Role.ROLE_USER);

			userRepository.save(user);
		}
	}
}