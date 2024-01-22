package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email must not be empty.")
    private String email;

    @NotBlank(message = "Password must not be empty.")
    private String password;
}
