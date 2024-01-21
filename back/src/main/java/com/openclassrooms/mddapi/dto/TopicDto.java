package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {

    private Long id;

    @Size(max = 100, message = "Name length must be 100 max.")
    @NotBlank(message = "Name must not be empty.")
    private String name;

    private LocalDateTime createdAt;
}
