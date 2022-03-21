package com.application.myapp.service.user;

import com.application.myapp.repository.UserRepository;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.model.User;
import com.application.myapp.model.UserRegistrationForm;
import com.application.myapp.model.UserEditForm;
import com.application.myapp.exception.UserNotFoundException;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

@Service
public class UserGetService {

	private UserRepository userRepository;

	@Autowired
	public UserGetService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();

		(userRepository.findAll())
			.forEach(
				userEntity -> 
					allUsers
						.add(User.toModel(userEntity)));

		return allUsers;
	}

	public User getUserById(Long id) throws UserNotFoundException {
		try {
			return User.toModel(userRepository.findById(id).get());

		} catch (Exception e) {
			throw new UserNotFoundException(String.format(
				"User not found. Details: %s", e.toString()));
		}
	}

	public User getUserByUsername(String username) throws UserNotFoundException {
		try {
			return User.toModel(userRepository.findByUsername(username));

		} catch (Exception e) {
			throw new UserNotFoundException(String.format(
				"User not found. Details: %s", e.toString()));
		}
	}
}