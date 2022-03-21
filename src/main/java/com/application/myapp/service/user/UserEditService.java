package com.application.myapp.service.user;

import com.application.myapp.repository.UserRepository;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.model.User;
import com.application.myapp.model.UserRegistrationForm;
import com.application.myapp.model.UserEditForm;
import com.application.myapp.exception.UserNotUpdatedException;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserEditService {

	private UserRepository userRepository;

	@Autowired
	public UserEditService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void editUser(String executorName, String username, UserEditForm userEditForm) 
		throws UserNotUpdatedException {

		try {
			UserEntity userEntity = userRepository.findByUsername(username);

			if (userEditForm.getUsername() != null) {
				userEntity.setUsername(userEditForm.getUsername());
			}

			if (userEditForm.getEmail() != null) {
				userEntity.setEmail(userEditForm.getEmail());
			}

			if (userEditForm.getPassword() != null) {
				userEntity.setPassword(userEditForm.getPassword());
			}

			userRepository.save(userEntity);

		} catch (Exception e) {
			throw new UserNotUpdatedException(String.format(
				"Failed to update user. Details: %s", e.toString()));
		}
	}

	public void editUserAsAdmin(String executorName, String username, UserEditForm userEditForm) 
		throws UserNotUpdatedException {

		try {
			UserEntity userEntity = userRepository.findByUsername(username);

			if (userEditForm.getUsername() != null) {
				userEntity.setUsername(userEditForm.getUsername());
			}

			if (userEditForm.getEmail() != null) {
				userEntity.setEmail(userEditForm.getEmail());
			}

			if (userEditForm.getPassword() != null) {
				userEntity.setPassword(userEditForm.getPassword());
			}

			userRepository.save(userEntity);
			
		} catch (Exception e) {
			throw new UserNotUpdatedException(String.format(
				"Failed to update user. Details: %s", e.toString()));
		}
	}
}