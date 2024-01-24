package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDetailDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.ICommentService;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@Validated
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping
    public ResponseEntity<MessageResponse> addPost(@Valid @RequestBody PostDto postDto, Principal principal) throws NotFoundException, BadRequestException {
        User user = userService.getByEmail(principal.getName());
        if (user == null) {
            throw new BadRequestException("Illegal operation");
        }

        postDto.setAuthorId(user.getId());
        postService.create(postMapper.toEntity(postDto));

        return ResponseEntity.ok(new MessageResponse("Post created successfully !"));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postMapper.toDtos(postService.findAllByCreatedAtDesc()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailDto> getById(@PathVariable("id") @NotNull @Positive Long id)
            throws NotFoundException {
        Post post = postService.getById(id);
        List<Comment> comments = commentService.getCommentsByPostId(id);

        return ResponseEntity.ok(new PostDetailDto(postMapper.toDto(post), commentMapper.toDtos(comments)));
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<MessageResponse> addComment(
            @PathVariable("id") @NotNull @Positive Long id,
            @Valid @RequestBody CommentDto commentDto,
            Principal principal) throws NotFoundException, BadRequestException {

        User user = userService.getByEmail(principal.getName());
        if (user == null) {
            throw new BadRequestException("Illegal operation");
        }

        commentService.addComment(commentMapper.toEntity(commentDto), user.getId(), id);

        return ResponseEntity.ok(new MessageResponse("Comment added successfully"));
    }
}
