package com.openclassrooms.mddapi.integration.service;

import com.openclassrooms.mddapi.exception.AlreadyExistsException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.IUserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    private User user;
    private Topic topic;

    @BeforeAll
    void setUp() {
        topic = topicRepository.save(Topic.builder().name("itest-user-topic").build());
    }

    @AfterAll
    void tearDown() {
        if (user != null) {
            userRepository.deleteById(user.getId());
        }
        topicRepository.deleteById(topic.getId());
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

        // update
        user.setEmail("updated@email.com");
        userService.update(user.getId(), user);
        assertEquals(user.getEmail(), userService.getById(user.getId()).getEmail());
        assertThrows(NotFoundException.class, () -> userService.update(10000L, user));

        // subscribe
        assertTrue(user.getTopics().isEmpty());
        userService.subscribe(user.getId(), topic.getId());
        assertEquals(topic.getId(), userService.getById(user.getId()).getTopics().get(0).getId());
        assertThrows(NotFoundException.class, () -> userService.subscribe(10000L, topic.getId()));
        assertThrows(BadRequestException.class, () -> userService.subscribe(user.getId(), topic.getId()));

        // unsubscribe
        userService.unsubscribe(user.getId(), topic.getId());
        assertTrue(userService.getById(user.getId()).getTopics().isEmpty());
        assertThrows(NotFoundException.class, () -> userService.unsubscribe(10000L, topic.getId()));
        assertThrows(BadRequestException.class, () -> userService.unsubscribe(user.getId(), topic.getId()));
    }
}
