package com.openclassrooms.mddapi.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @Size(max = 50, message = "Username length must be 50 max.")
    @NotBlank(message = "Username must not be empty.")
    private String username;

    @Size(max = 255, message = "Email length must be 255 max.")
    @Email(message = "Email must have email format.")
    @NotBlank(message = "Email must not be empty.")
    private String email;

    @Size(min = 8, max = 255, message = "Password length must be between 8 and 255.")
    @Pattern(
            regexp = "(?=.*[0-9])(?=.*[!:;,+?$-])(?=.*[a-z])(?=.*[A-Z]).*",
            message = "Password must contain at least one number, one uppercase, one lowercase and one special character.")
    private String password;
}
