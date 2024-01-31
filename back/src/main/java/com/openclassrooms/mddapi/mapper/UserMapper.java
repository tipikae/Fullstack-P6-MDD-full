package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * User mapper.
 * @author tipikae
 * @version 1.0.0
 */
@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
