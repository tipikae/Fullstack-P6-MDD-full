package com.openclassrooms.mddapi.integration.repository;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryITest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    void test() {
        // save
        User user = userRepository.save(
                User.builder()
                        .username("username-user")
                        .email("user@test.com")
                        .password("123456")
                        .build());
        assertNotNull(user);

        // get all
        assertFalse(userRepository.findAll().isEmpty());

        // get one by id
        assertTrue(userRepository.existsById(user.getId()));

        // exists by email or username
        assertTrue(userRepository.existsByEmailOrUsername(user.getEmail(), "unused_username"));
        assertTrue(userRepository.existsByEmailOrUsername("unused@email.com", user.getUsername()));
        assertFalse(userRepository.existsByEmailOrUsername("unused@email.com", "unused_username"));

        // find by email
        assertNotNull(userRepository.findByEmail(user.getEmail()));

        // delete
        userRepository.deleteById(user.getId());
        assertFalse(userRepository.existsById(user.getId()));
    }
}
