package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.response.ErrorResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.ICommentService;
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
 * Comment controller.
 * @author tipikae
 * @version 1.0.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/comment")
@Validated
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * Get comments of a post.
     * @param postId Post id.
     * @return ResponseEntity
     * @throws NotFoundException thrown when the post is not found.
     */
    @Operation(summary = "Get all comments of a post.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns all comments of a post.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }
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
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostID(@PathVariable("postId") @NotNull @Positive long postId) throws NotFoundException {
        // check if post with id=postId exists
        postService.getById(postId);

        List<Comment> comments = commentService.getCommentsByPostId(postId);

        return ResponseEntity.ok(commentMapper.toDtos(comments));
    }

    /**
     * Add a comment endpoint.
     * @param postId Post id.
     * @param commentDto Comment to add.
     * @param principal Current user.
     * @return ResponseEntity
     * @throws NotFoundException thrown when current user or post is not found.
     * @throws BadRequestException thrown when an error occurred during authentication.
     */
    @Operation(summary = "Add a comment")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment creation succeeded",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Field or path variable not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User or post not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
            )
    })
    @PostMapping("/post/{postId}")
    public ResponseEntity<MessageResponse> addComment(
            @PathVariable("postId") @NotNull @Positive Long postId,
            @Valid @RequestBody CommentDto commentDto,
            Principal principal) throws NotFoundException, BadRequestException {

        User user = userService.getByEmail(principal.getName());
        if (user == null) {
            throw new BadRequestException("Illegal operation");
        }

        commentService.addComment(commentMapper.toEntity(commentDto), user.getId(), postId);

        return ResponseEntity.ok(new MessageResponse("Comment added successfully"));
    }
}
