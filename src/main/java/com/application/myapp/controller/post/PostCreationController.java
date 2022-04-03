package com.application.myapp.controller.post;

import com.application.myapp.service.post.PostCreationService;
import com.application.myapp.entity.post.PostEntity;
import com.application.myapp.exception.post.PostNotCreatedException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import java.security.Principal;
import org.springframework.ui.Model;


@Controller
public class PostCreationController {
	
	private PostCreationService postCreationService;

	@Autowired
	public PostCreationController(PostCreationService postCreationService) {
		this.postCreationService = postCreationService;
	}

	@GetMapping("/posts/create")
	@PreAuthorize("hasAuthority('CREATE_POSTS')")
	public String postCreationPage(@ModelAttribute PostEntity postEntity) {
		return "/post/";
	}

	@PostMapping("/posts/create")
	@PreAuthorize("hasAuthority('CREATE_POSTS')")
	public String createPost(@Valid PostEntity postEntity, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return "/post/";
			}

			postCreationService.createPost(postEntity);

			return "";

		} catch (PostNotCreatedException e) {
			return e.getMessage();
		}
	}
}
