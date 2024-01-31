package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ITopicService;
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
@Mapper(
        componentModel = "spring",
        uses = { ITopicService.class, TopicMapper.class },
        imports = { Arrays.class, Collectors.class, User.class, Topic.class, TopicDto.class, Collections.class,
                Optional.class, NotFoundException.class }
)
public abstract class UserMapper implements EntityMapper<UserDto, User> {

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    ITopicService topicService;

    /**
     * Convert a User DTO to a User.
     *
     * @param userDto User DTO.
     * @return User
     * @throws NotFoundException thrown when user is not found.
     */
    @Override
    @Mapping(
            target = "topics",
            expression = "java(Optional.ofNullable(userDto.getTopics()).orElseGet(Collections::emptyList).stream()" +
                    ".map(topicDto -> { Topic topic = null; try { topic = topicService.getById(topicDto.getId()); } catch (NotFoundException e) {} return topic; } )" +
                    ".collect(Collectors.toList()))"
    )
    public abstract User toEntity(UserDto userDto) throws NotFoundException;

    /**
     * Convert a User to a User DTO.
     *
     * @param user User.
     * @return UserDto
     */
    @Override
    @Mapping(
            target = "topics",
            expression = "java(Optional.ofNullable(user.getTopics()).orElseGet(Collections::emptyList).stream()" +
                    ".map(topic -> topicMapper.toDto(topic))" +
                    ".collect(Collectors.toList()))"
    )
    public abstract UserDto toDto(User user);
}
