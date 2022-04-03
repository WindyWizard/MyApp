package com.application.myapp.service.post;

import com.application.myapp.repository.post.PostRepository;
import com.application.myapp.entity.post.PostEntity;
import com.application.myapp.exception.post.PostNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class PostGetService {

	private PostRepository postRepository;

	@Autowired
	public PostGetService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<PostEntity> getAllPosts() {
		List<PostEntity> posts = new ArrayList<>();

		(postRepository.findAll())
			.forEach(post -> posts.add(post));

		return posts;
	}

	public PostEntity getPostByTitle(String title) throws PostNotFoundException {
		try {
			return postRepository.findByTitle(title);

		} catch (Exception e) {
			throw new PostNotFoundException(String.format(
				"Post not found. Details: %s", e.toString()));
		}
	}
}