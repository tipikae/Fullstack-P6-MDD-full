package com.openclassrooms.mddapi.integration.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.ICommentService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentServiceITest {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    private User user;
    private Post post;
    private Topic topic;
    private Comment comment;

    @BeforeAll
    void setUp() {
        user = userRepository.save(User.builder()
                .username("itest-comment-username")
                .email("itest-comment@email.com")
                .password("123456Qg+")
                .build());

        topic = topicRepository.save(Topic.builder()
                .name("itest-comment-topic")
                .build());

        post = postRepository.save(Post.builder()
                .title("itest-comment-title")
                .content("itest-comment-content")
                .topic(topic)
                .user(user)
                .build());
    }

    @AfterAll
    void tearDown() {
        if (comment != null) {
            commentRepository.deleteById(comment.getId());
        }
        postRepository.deleteById(post.getId());
        userRepository.deleteById(user.getId());
        topicRepository.deleteById(topic.getId());
    }

    @Test
    void test() throws NotFoundException {
        // create
        comment = commentService.addComment(
                Comment.builder().comment("itest-comment").build(), user.getId(), post.getId());
        assertNotNull(comment);
        assertThrows(NotFoundException.class, () -> commentService.addComment(
                Comment.builder().comment("itest-comment").build(), 10000L, post.getId()));
        assertThrows(NotFoundException.class, () -> commentService.addComment(
                Comment.builder().comment("itest-comment").build(), user.getId(), 10000L));

        // find comments by post id
        assertEquals(1, commentService.getCommentsByPostId(post.getId()).size());
        assertTrue(commentService.getCommentsByPostId(10000L).isEmpty());
    }
}
