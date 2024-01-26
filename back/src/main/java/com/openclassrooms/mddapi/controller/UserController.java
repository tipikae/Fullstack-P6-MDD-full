package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exception.AlreadyExistsException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

/**
 * User controller.
 * @author tipikae
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Get current user profile endpoint.
     * @param principal Current user.
     * @return ResponseEntity
     * @throws NotFoundException thrown when current user is not found.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getProfile(Principal principal) throws NotFoundException {
        User user = userService.getByEmail(principal.getName());
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    /**
     * Update current user profile endpoint.
     * @param id User id.
     * @param updateRequest User information updated.
     * @param principal Current user.
     * @return ResponseEntity
     * @throws NotFoundException thrown when user is not found.
     * @throws BadRequestException thrown when an error occurred during authentication.
     * @throws AlreadyExistsException thrown when email or username is already taken.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateProfile(
            @PathVariable("id") @NotNull @Positive Long id,
            @Valid @RequestBody RegisterRequest updateRequest,
            Principal principal)
            throws NotFoundException, BadRequestException, AlreadyExistsException {

        User user = userService.getByEmail(principal.getName());
        if (user == null || !Objects.equals(user.getId(), id)) {
            throw new BadRequestException("Illegal operation.");
        }

        userService.update(id, User.builder()
                .username(updateRequest.getUsername())
                .email(updateRequest.getEmail())
                .password(updateRequest.getPassword())
                .build());

        return ResponseEntity.ok(new MessageResponse("User updated successfully !"));
    }
}
