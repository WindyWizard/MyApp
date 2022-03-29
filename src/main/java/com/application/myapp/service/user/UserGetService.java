package com.application.myapp.service.user;

import com.application.myapp.repository.UserRepository;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.model.User;
import com.application.myapp.exception.UserNotFoundException;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import java.util.stream.Collectors;

@Service
public class UserGetService {

	private UserRepository userRepository;

	@Autowired
	public UserGetService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getOtherUsers(String username) {
		List<User> users = new ArrayList<>();

		(userRepository.findAll())
			.forEach(user -> users.add(User.toModel(user)));

		return users.stream()
			.filter(user -> !username.equals(user.getUsername()))
				.collect(Collectors.toList());
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