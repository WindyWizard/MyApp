package com.application.myapp.controller.comment;

import com.application.myapp.service.comment.CommentService;
import com.application.myapp.entity.comment.CommentEntity;
import com.application.myapp.exception.comment.CommentNotCreatedException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import java.security.Principal;
import org.springframework.ui.Model;

@Controller
public class CommentController {
	
	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/posts/{title}/comment")
	@PreAuthorize("hasAuthority('READ_ANY_PROFILE')")
	public String createComment(@PathVariable("title") String title, String comment, Principal principal) {

		try {
			commentService.createComment(title, principal.getName(), comment);

			return "redirect:/posts/selected/{title}";

		} catch (CommentNotCreatedException e) {
			return e.getMessage();
		}
	}
}
