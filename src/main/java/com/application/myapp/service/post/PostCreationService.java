package com.application.myapp.service.post;

import com.application.myapp.repository.post.PostRepository;
import com.application.myapp.entity.post.PostEntity;
import com.application.myapp.exception.post.PostNotCreatedException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PostCreationService {

	private PostRepository postRepository;

	@Autowired
	public PostCreationService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void createPost(String username, PostEntity postEntity, String image) throws PostNotCreatedException {
		try {
			postEntity.setAuthor(username);
			postEntity.setImage(image);
			postRepository.save(postEntity);

		} catch (Exception e) {
			throw new PostNotCreatedException(String.format(
				"Failed to creation post. Details: %s", e.toString()));
		}
	}
}