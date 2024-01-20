package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @NotNull(message = "Author id must not be null.")
    private long authorId;

    @NotNull(message = "Topic must not be null.")
    private long topicId;

    private LocalDateTime createdAt;
}
