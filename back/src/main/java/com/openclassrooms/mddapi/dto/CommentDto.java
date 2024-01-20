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
public class CommentDto {

    private long id;

    @Size(max = 255, message = "Comment length must be 255 max.")
    @NotBlank(message = "Comment must not be empty.")
    private String comment;

    private String author;

    private LocalDateTime createdAt;
}
