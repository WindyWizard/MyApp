package com.application.myapp.service.user;

import com.application.myapp.repository.UserRepository;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.model.User;
import com.application.myapp.model.UserRegistrationForm;
import com.application.myapp.model.UserEditForm;
import com.application.myapp.exception.UserNotDeletedException;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserDeleteService {

	private UserRepository userRepository;

	@Autowired
	public UserDeleteService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void deleteUser(String username) throws UserNotDeletedException {
		try {
			userRepository.deleteByUsername(username);

		} catch (Exception e) {
			throw new UserNotDeletedException(String.format(
				"Failed to delete user. Details: %s", e.toString()));
		}
	}
}