package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Login request POJO.
 * @author tipikae
 * @version 1.0.0
 */
@Data
public class LoginRequest {

    @NotBlank(message = "Email must not be empty.")
    private String email;

    @NotBlank(message = "Password must not be empty.")
    private String password;
}
