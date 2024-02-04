package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.response.ErrorResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Post controller.
 * @author tipikae
 * @version  1.0.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/post")
@Validated
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @Autowired
    private PostMapper postMapper;

    /**
     * Add post endpoint.
     * @param postDto Post to add.
     * @param principal Current user.
     * @return ResponseEntity
     * @throws NotFoundException thrown when the current user is not found.
     * @throws BadRequestException thrown when an error occurred during authentication.
     */
    @Operation(summary = "Add a post")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Post creation succeeded",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Field not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User or topic is not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            )
    })
    @PostMapping
    public ResponseEntity<MessageResponse> addPost(@Valid @RequestBody PostDto postDto, Principal principal)
            throws NotFoundException, BadRequestException {
        User user = userService.getByEmail(principal.getName());
        if (user == null) {
            throw new BadRequestException("Illegal operation");
        }

        postDto.setAuthorId(user.getId());
        postService.create(postMapper.toEntity(postDto));

        return ResponseEntity.ok(new MessageResponse("Post created successfully !"));
    }

    /**
     * Get all posts endpoint.
     * @return ResponseEntity
     */
    @Operation(summary = "Get all posts")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns all posts",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }

            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            )
    })
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postMapper.toDtos(postService.findAllByCreatedAtDesc()));
    }

    /**
     * Get a post by id.
     * @param id Post id.
     * @return ResponseEntity
     * @throws NotFoundException thrown when the post is not found.
     */
    @Operation(summary = "Get a post by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns a post",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Path variable not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Post not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") @NotNull @Positive Long id)
            throws NotFoundException {
        Post post = postService.getById(id);
        return ResponseEntity.ok(postMapper.toDto(post));
    }
}
