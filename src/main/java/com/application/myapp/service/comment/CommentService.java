package com.application.myapp.service.comment;

import com.application.myapp.repository.comment.CommentRepository;
import com.application.myapp.repository.post.PostRepository;
import com.application.myapp.entity.comment.CommentEntity;
import com.application.myapp.exception.comment.CommentNotCreatedException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	public void createComment(String title, String username, String comment) throws CommentNotCreatedException {
		try {
			CommentEntity commentEntity = new CommentEntity();
			commentEntity.setAuthor(username);
			commentEntity.setComment(comment);
			commentEntity.setPostId(postRepository.findByTitle(title));
			commentRepository.save(commentEntity);

		} catch (Exception e) {
			throw new CommentNotCreatedException(String.format(
				"Failed to creation comment. Details: %s", e.toString()));
		}
	}
}