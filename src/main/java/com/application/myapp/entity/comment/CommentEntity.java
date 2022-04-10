package com.application.myapp.entity.comment;

import com.application.myapp.entity.post.PostEntity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "comments")
public class CommentEntity {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "author")
	private String author;

	@NotEmpty(message = "Comment cannot be empty")
	@Column(name = "comment")
	private String comment;

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private PostEntity postId;
}