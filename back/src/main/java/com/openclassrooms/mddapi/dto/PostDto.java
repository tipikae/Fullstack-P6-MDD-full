package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Post DTO.
 * @author tipikae
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    @Size(max = 50, message = "Title length must be 50 max.")
    @NotBlank(message = "Title must not be empty.")
    private String title;

    @Size(max = 2000, message = "Content length must be 2000 max.")
    @NotBlank(message = "Content must not be empty.")
    private String content;

    private Long authorId;

    private String authorUsername;

    @NotNull(message = "Topic id must not be empty.")
    private Long topicId;

    private String topicName;

    private LocalDateTime createdAt;
}
