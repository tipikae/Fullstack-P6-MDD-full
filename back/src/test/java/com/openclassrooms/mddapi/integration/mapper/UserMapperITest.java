package com.openclassrooms.mddapi.integration.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
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
    void test() {
        User user = User.builder()
                .username("itest-mapper-user")
                .email("itest-user@mapper.com")
                .password("123456Mn+")
                .build();

        // to dto
        UserDto userDto = userMapper.toDto(user);
        assertEquals(user.getEmail(), userDto.getEmail());
        assertNotNull(userDto.getTopicIds());
        assertTrue(userDto.getTopicIds().isEmpty());

        // to entity
        assertEquals(userDto.getUsername(), userMapper.toEntity(userDto).getUsername());
    }
}
