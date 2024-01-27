package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.TopicService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User mapper.
 * @author tipikae
 * @version 1.0.0
 */
@Component
@Mapper(componentModel = "spring", uses = {TopicService.class}, imports = {Arrays.class, Collectors.class, User.class,
        Topic.class, Collections.class, Optional.class, NotFoundException.class})
public abstract class UserMapper implements EntityMapper<UserDto, User> {

    /**
     * Topic service.
     */
    @Autowired
    protected TopicService topicService;

    /**
     * Convert a DTO to an entity.
     * @param userDto DTO
     * @return User
     */
    @Override
    @Mapping(
            target = "topics",
            expression = "java(Optional.ofNullable(userDto.getTopicIds()).orElseGet(Collections::emptyList).stream()" +
                    ".map(topicId -> { Topic topic = null; try { topic = topicService.getById(topicId); } catch(NotFoundException e) {} if (topic != null) { return topic; } return null; })" +
                    ".collect(Collectors.toList()))"
    )
    public abstract User toEntity(UserDto userDto);

    /**
     * Convert an entity to a DTO.
     * @param user Entity
     * @return UserDto
     */
    @Override
    @Mapping(
            target = "topicIds",
            expression = "java(Optional.ofNullable(user.getTopics()).orElseGet(Collections::emptyList).stream()" +
                    ".map(topic -> topic.getId())" +
                    ".collect(Collectors.toList()))"
    )
    public abstract UserDto toDto(User user);
}
