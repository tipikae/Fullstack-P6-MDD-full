package com.openclassrooms.mddapi.integration.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMapperITest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test() throws NotFoundException {
        User user = User.builder()
                .username("userMapper")
                .email("user@mapper.com")
                .password("123456Mn+")
                .build();

        // to dto
        UserDto userDto = userMapper.toDto(user);
        assertEquals(user.getEmail(), userDto.getEmail());

        // to entity
        assertEquals(userDto.getUsername(), userMapper.toEntity(userDto).getUsername());
    }
}
