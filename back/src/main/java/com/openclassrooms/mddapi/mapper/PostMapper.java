package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {UserService.class, TopicService.class}, imports = {Post.class, NotFoundException.class})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    @Mappings({
            @Mapping(
                    target = "author",
                    expression = "java(postDto.getAuthorId() != null ? userService.getById(postDto.getAuthorId()): null)"
            ),
            @Mapping(
                    target = "topic",
                    expression = "java(postDto.getTopicId() != null ? topicService.getById(postDto.getTopicId()) : null)"
            )
    })
    public abstract Post toEntity(PostDto postDto) throws NotFoundException;

    @Mappings({
            @Mapping( target = "authorId", source = "post.author.id"),
            @Mapping( target = "authorUsername", source = "post.author.username"),
            @Mapping( target = "topicId", source = "post.topic.id"),
            @Mapping( target = "topicName", source = "post.topic.name")
    })
    public abstract PostDto toDto(Post post);
}
