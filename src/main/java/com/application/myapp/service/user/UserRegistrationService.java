package com.application.myapp.service.user;

import com.application.myapp.repository.user.UserRepository;
import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.exception.user.UserNotRegisteredException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserRegistrationService {

	private UserRepository userRepository;

	@Autowired
	public UserRegistrationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void registerUser(UserEntity userEntity) throws UserNotRegisteredException {
		try {
			userEntity.encodePassword();
			userRepository.save(userEntity);

		} catch (Exception e) {
			throw new UserNotRegisteredException(String.format(
				"Failed to registration user. Details: %s", e.toString()));
		}
	}
}