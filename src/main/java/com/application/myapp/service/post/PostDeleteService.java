package com.application.myapp.service.post;

import com.application.myapp.repository.post.PostRepository;
import com.application.myapp.entity.post.PostEntity;
import com.application.myapp.exception.post.PostNotDeletedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PostDeleteService {

	private PostRepository postRepository;

	@Autowired
	public PostDeleteService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Transactional
	public void deletePost(String title) throws PostNotDeletedException {
		try {
			postRepository.deleteByTitle(title);

		} catch (Exception e) {
			throw new PostNotDeletedException(String.format(
				"Failed to delete post. Details: %s", e.toString()));
		}
	}
}