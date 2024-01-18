package com.openclassrooms.mddapi.integration.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.PostService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostServiceITest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    private Post post;
    private User user;
    private Topic topic;

    @BeforeAll
    void setUp() {
        user = userRepository.save(
                User.builder()
                        .email("itestpost@email.com")
                        .username("itestpost-username")
                        .password("123456Tr+")
                        .build());

        topic = topicRepository.save(Topic.builder().name("itestpost-topic").build());
    }

    @AfterAll
    void tearDown() {
        if (post != null) {
            postRepository.deleteById(post.getId());
        }
        userRepository.deleteById(user.getId());
        topicRepository.deleteById(topic.getId());
    }

    @Test
    void test() throws NotFoundException {
        // create
        post = postService.create(
                Post.builder()
                        .title("itest-title")
                        .content("itest-content")
                        .author(user)
                        .topic(topic)
                        .build());
        assertNotNull(post);

        // get by id
        assertNotNull(postService.getById(post.getId()));
        assertThrows(NotFoundException.class, () -> postService.getById(10000L));

        // find all
        assertFalse(postService.findAllByCreatedAtDesc().isEmpty());
    }
}
