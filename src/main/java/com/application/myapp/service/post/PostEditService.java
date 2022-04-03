package com.application.myapp.service.post;

import com.application.myapp.repository.post.PostRepository;
import com.application.myapp.entity.post.PostEntity;
import com.application.myapp.model.post.PostEditingForm;
import com.application.myapp.exception.post.PostNotEditedException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PostEditService {

	private PostRepository postRepository;

	@Autowired
	public PostEditService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void editPost(String title, PostEditingForm postEditingForm) 
		throws PostNotEditedException {

		try {
			PostEntity edited = postRepository.findByTitle(title);
			edited.setTitle(postEditingForm.getTitle());
			edited.setContent(postEditingForm.getContent());
			postRepository.save(edited);

		} catch (Exception e) {
			throw new PostNotEditedException(String.format(
				"Failed to edit post. Details: %s", e.toString()));
		}
	}
}