package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.IUserService;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Comment Mapper.
 * @author tipikae
 * @version 1.0.0
 */
@Component
@Mapper(componentModel = "spring", uses = {UserService.class, PostService.class}, imports = {Comment.class})
public abstract class CommentMapper implements EntityMapper<CommentDto, Comment> {

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

    /**
     * Convert a DTO to an entity.
     * @param commentDto DTO
     * @return Comment
     * @throws NotFoundException thrown when comment is not found.
     */
    @Override
    @Mappings({
            @Mapping(target = "user", expression = "java(commentDto.getUserId() != null ? userService.getById(commentDto.getUserId()) : null)"),
            @Mapping(target = "post", expression = "java(commentDto.getPostId() != null ? postService.getById(commentDto.getPostId()) : null)")
    })
    public abstract Comment toEntity(CommentDto commentDto) throws NotFoundException;

    /**
     * Convert an entity to a DTO.
     * @param comment Entity
     * @return CommentDto
     */
    @Override
    @Mappings({
            @Mapping(target = "userId", source = "comment.user.id"),
            @Mapping(target = "userName", source = "comment.user.username"),
            @Mapping(target = "postId", source = "comment.post.id")
    })
    public abstract CommentDto toDto(Comment comment);
}
