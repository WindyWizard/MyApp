package com.application.myapp.repository.comment;

import com.application.myapp.entity.comment.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
	
}