package com.application.myapp.model.post;

import com.application.myapp.entity.post.PostEntity;
import lombok.*;
import javax.validation.constraints.*;

@Data
public class PostEditingForm {

	@NotEmpty(message = "Title cannot be empty")
	@Size(min = 2, max = 20, message = "Title must be between 2 and 20 characters")
	private String title;

	@NotEmpty(message = "Content cannot be empty")
	private String content;

	public PostEditingForm() {
		
	}

	public PostEditingForm(String title, String content) {
		this.title = title;
		this.content = content;
	}
}