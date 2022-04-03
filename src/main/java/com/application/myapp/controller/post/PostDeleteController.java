package com.application.myapp.controller.post;

import com.application.myapp.service.post.PostDeleteService;
import com.application.myapp.entity.post.PostEntity;
import com.application.myapp.exception.post.PostNotDeletedException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class PostDeleteController {
	
	private PostDeleteService postDeleteService;

	@Autowired
	public PostDeleteController(PostDeleteService postDeleteService) {
		this.postDeleteService = postDeleteService;
	}

	@PostMapping("/posts/delete/{title}")
	@PreAuthorize("hasAuthority('DELETE_POSTS')")
	public String deletePost(@PathVariable("title") String title, Model model) {
		try {
			postDeleteService.deletePost(title);

			return "";

		} catch (PostNotDeletedException e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}
}