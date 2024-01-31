package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User DTO.
 * @author tipikae
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private List<Topic> topics;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
