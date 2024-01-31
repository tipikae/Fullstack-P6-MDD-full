package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Update profile request.
 * @author tipikae
 * @version  1.0.0
 */
@Data
public class UpdateProfileRequest {

    @Size(max = 50, message = "Username length must be 50 max.")
    @NotBlank(message = "Username must not be empty.")
    private String username;

    @Size(max = 255, message = "Email length must be 255 max.")
    @Email(message = "Email must have email format.")
    @NotBlank(message = "Email must not be empty.")
    private String email;
}
