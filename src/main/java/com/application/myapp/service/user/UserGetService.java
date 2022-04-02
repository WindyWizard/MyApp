package com.application.myapp.service.user;

import com.application.myapp.repository.UserRepository;
import com.application.myapp.entity.UserEntity;
import com.application.myapp.model.User;
import com.application.myapp.entity.Role;
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
		List<UserEntity> entities = new ArrayList<>();

		(userRepository.findAll())
			.forEach(user -> entities.add(user));

		List<User> models = new ArrayList<>();

		entities.stream()
			.filter(entity -> entity.getRole() != Role.ROLE_SUPERADMIN)
				.forEach(e -> models.add(User.toModel(e)));

		List<User> result = new ArrayList<>();

		models.stream()
			.filter(model -> !model.getUsername().equals(username))
				.forEach(m -> result.add(m));

		return result;
	}

	public User getUserByUsername(String username) throws UserNotFoundException {
		try {
			return User.toModel(userRepository.findByUsername(username));

		} catch (Exception e) {
			throw new UserNotFoundException(String.format(
				"User not found. Details: %s", e.toString()));
		}
	}

	public UserEntity getUserEntityByUsername(String username) throws UserNotFoundException {
		try {
			return userRepository.findByUsername(username);

		} catch (Exception e) {
			throw new UserNotFoundException(String.format(
				"User not found. Details: %s", e.toString()));
		}
	}
}