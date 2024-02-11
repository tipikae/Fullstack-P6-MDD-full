package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.ITopicService;
import com.openclassrooms.mddapi.service.IUserService;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Post mapper.
 * @author tipikae
 * @version 1.0.0
 */
@Component
@Mapper(componentModel = "spring", uses = {UserService.class, TopicService.class}, imports = {Post.class, NotFoundException.class})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {

    @Autowired
    IUserService userService;

    @Autowired
    ITopicService topicService;

    /**
     * Convert a DTO to an entity.
     * @param postDto DTO
     * @return Post
     * @throws NotFoundException thrown when entity is not found.
     */

    @Override
    @Mappings({
            @Mapping(
                    target = "user",
                    expression = "java(postDto.getUserId() != null ? userService.getById(postDto.getUserId()): null)"
            ),
            @Mapping(
                    target = "topic",
                    expression = "java(postDto.getTopicId() != null ? topicService.getById(postDto.getTopicId()) : null)"
            )
    })
    public abstract Post toEntity(PostDto postDto) throws NotFoundException;

    /**
     * Convert an entity to a DTO.
     * @param post Entity
     * @return PostDto
     */
    @Override
    @Mappings({
            @Mapping( target = "userId", source = "post.user.id"),
            @Mapping( target = "userName", source = "post.user.username"),
            @Mapping( target = "topicId", source = "post.topic.id"),
            @Mapping( target = "topicName", source = "post.topic.name")
    })
    public abstract PostDto toDto(Post post);
}
