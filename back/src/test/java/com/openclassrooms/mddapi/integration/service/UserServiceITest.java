package com.openclassrooms.mddapi.integration.service;

import com.openclassrooms.mddapi.exception.AlreadyExistsException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceITest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @AfterAll
    void tearDown() {
        if (user != null) {
            userRepository.deleteById(user.getId());
        }
    }

    @Test
    void test() throws AlreadyExistsException, NotFoundException, BadRequestException {
        // create
        user = userService.create(
                User.builder()
                        .username("itest-username")
                        .email("itest@email.com")
                        .password("123456Xz+")
                        .topics(new ArrayList<>())
                        .build());
        assertNotNull(user);
        assertThrows(AlreadyExistsException.class, () -> userService.create(
                User.builder()
                        .username("itest-username")
                        .email("itest@email.com")
                        .password("123456Xz+")
                        .topics(new ArrayList<>())
                        .build()));

        // get by id
        assertNotNull(userService.getById(user.getId()));
        assertThrows(NotFoundException.class, () -> userService.getById(10000L));

        // subscribe
        assertTrue(user.getTopics().isEmpty());
        userService.subscribe(user.getId(), 1L);
        assertEquals(1L, userService.getById(user.getId()).getTopics().get(0).getId());
        assertThrows(NotFoundException.class, () -> userService.subscribe(10000L, 1L));
        assertThrows(BadRequestException.class, () -> userService.subscribe(user.getId(), 1L));

        // unsubscribe
        userService.unsubscribe(user.getId(), 1L);
        assertTrue(userService.getById(user.getId()).getTopics().isEmpty());
        assertThrows(NotFoundException.class, () -> userService.unsubscribe(10000L, 1L));
        assertThrows(BadRequestException.class, () -> userService.unsubscribe(user.getId(), 1L));
    }
}
