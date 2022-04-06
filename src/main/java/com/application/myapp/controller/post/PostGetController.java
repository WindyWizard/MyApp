package com.application.myapp.controller.post;

import com.application.myapp.service.post.PostGetService;
import com.application.myapp.entity.post.PostEntity;
import com.application.myapp.exception.post.PostNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import java.security.Principal;

@Controller
public class PostGetController {
	
	private PostGetService postService;

	@Autowired
	public PostGetController(PostGetService postService) {
		this.postService = postService;
	}

	@GetMapping("/posts/all")
	@PreAuthorize("hasAuthority('READ_POSTS')")
	public String getAllPosts(Principal principal, Model model) {
		model.addAttribute("posts",  postService.getAllPosts());
		return "/post/read/all";
	}

	@GetMapping("/posts/selected/{title}")
	@PreAuthorize("hasAuthority('READ_POSTS')")
	public String getPostByTitle(@PathVariable("title") String title, Principal principal, Model model) 
	throws PostNotFoundException {
		model.addAttribute("post", postService.getPostByTitle(title));
		return "/post/read/selected";
	}
}