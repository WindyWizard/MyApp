package com.application.myapp.service.user;

import com.application.myapp.repository.UserRepository;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.model.User;
import com.application.myapp.model.UserRegistrationForm;
import com.application.myapp.model.UserEditForm;
import com.application.myapp.exception.UserNotCreatedException;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserRegistrationService {

	private UserRepository userRepository;

	@Autowired
	public UserRegistrationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void registerUser(UserRegistrationForm userRegistrationForm) throws UserNotCreatedException {
		try {
			userRepository.save(UserEntity.toEntity(userRegistrationForm));

		} catch (Exception e) {
			throw new UserNotCreatedException(String.format(
				"Failed to create user. Details: %s", e.toString()));
		}
	}
}