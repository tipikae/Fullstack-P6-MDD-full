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
 * Topic model.
 * @author tipikae
 * @version 1.0.0
 */
@Entity
@Table(name = "TOPICS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 100)
	@NotNull
	private String name;

	@Size(max = 255)
	@NotNull
	private String description;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
}
