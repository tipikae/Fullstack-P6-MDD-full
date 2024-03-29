package com.openclassrooms.mddapi.integration.repository;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostRepositoryITest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    private User author;
    private Topic topic;

    @BeforeAll
    void setUp() {
        // save user
        author = userRepository.save(
                User.builder()
                        .username("username-post")
                        .email("post@test.com")
                        .password("13456")
                        .build());

        // save topic
        topic = topicRepository.save(Topic.builder().name("topic-name").description("test").build());
    }

    @AfterAll
    void tearDown() {
        topicRepository.deleteById(topic.getId());
        userRepository.deleteById(author.getId());
    }

    @Test
    void test() {
        // save
        Post post = postRepository.save(
                Post.builder()
                        .title("post-title")
                        .content("post-content")
                        .user(author)
                        .topic(topic)
                        .build());
        assertNotNull(post);

        // get all
        assertFalse(postRepository.findAll().isEmpty());

        // get one by id
        assertTrue(postRepository.existsById(post.getId()));

        // delete
        postRepository.deleteById(post.getId());
        assertFalse(postRepository.existsById(post.getId()));
    }
}
