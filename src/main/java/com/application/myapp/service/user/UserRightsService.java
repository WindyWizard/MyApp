package com.application.myapp.service.user;

import com.application.myapp.model.user.UserRightsEditingForm;
import com.application.myapp.model.user.User;
import com.application.myapp.repository.user.UserRepository;
import com.application.myapp.entity.user.UserEntity;
import com.application.myapp.entity.user.Role;
import com.application.myapp.exception.user.UserRightsEditedException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserRightsService {

	private UserRepository userRepository;

	@Autowired
	public UserRightsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void editUserRights(String username, UserRightsEditingForm userRightsEditingForm) throws UserRightsEditedException{
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