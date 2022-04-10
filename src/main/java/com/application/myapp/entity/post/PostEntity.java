package com.application.myapp.entity.post;

import com.application.myapp.entity.comment.CommentEntity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@Table(name = "posts")
public class PostEntity {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "author")
	private String author;

	@NotEmpty(message = "Title cannot be empty")
	@Size(min = 2, max = 20, message = "Title must be between 2 and 20 characters")
	@Column(name = "title")
	private String title;

	@NotEmpty(message = "Content cannot be empty")
	@Column(name = "content")
	private String content;

	@Column(name = "image")
	private String image;

	@Column(name = "comments")
	@OneToMany(mappedBy = "postId", fetch = FetchType.EAGER)
	private List<CommentEntity> comments = new ArrayList<>();
}