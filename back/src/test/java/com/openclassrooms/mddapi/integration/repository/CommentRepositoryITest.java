package com.openclassrooms.mddapi.integration.repository;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
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
public class CommentRepositoryITest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    TopicRepository topicRepository;

    private User author;
    private Topic topic;
    private Post post;

    @BeforeAll
    void setUp() {
        // save user
        author = userRepository.save(
                User.builder()
                        .username("username-comment")
                        .email("comment@test.com")
                        .password("13456")
                        .build());

        // save topic
        topic = topicRepository.save(Topic.builder().name("name-comment").description("test").build());

        // save post
        post = postRepository.save(
                Post.builder()
                        .title("title")
                        .content("content")
                        .user(author)
                        .topic(topic)
                        .build());
    }

    @AfterAll
    void tearDown() {
        postRepository.deleteById(post.getId());
        topicRepository.deleteById(topic.getId());
        userRepository.deleteById(author.getId());
    }

    @Test
    void test() {
        // save
        Comment comment = commentRepository.save(
                Comment.builder()
                        .comment("comment")
                        .user(author)
                        .post(post)
                        .build());
        assertNotNull(comment);

        // get all
        assertFalse(commentRepository.findAll().isEmpty());

        // get one by id
        assertTrue(commentRepository.existsById(comment.getId()));

        // find by post id
        assertEquals(1, commentRepository.findByPostId(post.getId()).size());

        // delete
        commentRepository.deleteById(comment.getId());
        assertFalse(commentRepository.existsById(comment.getId()));
    }
}
