package com.application.myapp.controller.post;

import com.application.myapp.service.post.PostEditService;
import com.application.myapp.service.post.PostGetService;
import com.application.myapp.entity.post.PostEntity;
import com.application.myapp.model.post.PostEditingForm;
import com.application.myapp.exception.post.PostNotEditedException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.validation.Valid;
import org.springframework.ui.Model;
import java.security.Principal;

@Controller
public class PostEditController {
	
	private PostEditService postEditService;
	private PostGetService postGetService;

	@Autowired
	public PostEditController(PostEditService postEditService, PostGetService postGetService) {
		this.postEditService = postEditService;
		this.postGetService = postGetService;
	}

	@GetMapping("/posts/edit/{title}")
	@PreAuthorize("hasAuthority('EDIT_POSTS')")
	public String postEditingPage(@PathVariable("title") String title, Principal principal, Model model) {
		try {
			model.addAttribute("postEditingForm", 
				new PostEditingForm(title, postGetService.getPostByTitle(title).getContent()));
			return "/post/edit";	

		} catch (Exception e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}

	@PostMapping("/posts/edit/{title}")
	@PreAuthorize("hasAuthority('EDIT_POSTS')")
	public String editPost(@PathVariable("title") String title, Model model,
		@Valid PostEditingForm postEditingForm, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				return "/message/error";
			}

			postEditService.editPost(title, postEditingForm);

			return "redirect:/posts/all";

		} catch (PostNotEditedException e) {
			model.addAttribute("error", e);
			return "/message/error";
		}
	}
}