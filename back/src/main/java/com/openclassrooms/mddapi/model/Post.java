package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Post model.
 * @author tipikae
 * @version 1.0.0
 */
@Entity
@Table(name = "POSTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 50)
	@NotNull
	private String title;

	@Size(max = 2000)
	@NotNull
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "topic_id")
	@NotNull
	private Topic topic;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
}
