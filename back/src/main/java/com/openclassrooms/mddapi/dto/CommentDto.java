package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Comment DTO.
 * @author tipikae
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;

    @Size(max = 255, message = "Comment length must be 255 max.")
    @NotBlank(message = "Comment must not be empty.")
    private String comment;

    private Long authorId;

    private String authorUsername;

    private Long postId;

    private LocalDateTime createdAt;
}
