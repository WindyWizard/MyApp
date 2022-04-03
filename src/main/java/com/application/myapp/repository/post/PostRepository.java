package com.application.myapp.repository.post;

import com.application.myapp.entity.post.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
	PostEntity findByTitle(String title);
	void deleteByTitle(String title);
}