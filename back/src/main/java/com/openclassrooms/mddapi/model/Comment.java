package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 255)
	private String comment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
}
