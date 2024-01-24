package com.openclassrooms.mddapi.integration.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.IPostRepository;
import com.openclassrooms.mddapi.repository.ITopicRepository;
import com.openclassrooms.mddapi.repository.IUserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentMapperITest {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private ITopicRepository topicRepository;

    private User author;
    private Topic topic;
    private Post post;

    @BeforeAll
    void setUp() {
        author = userRepository.save(
                User.builder()
                        .username("username")
                        .email("test@test.com")
                        .password("123456Qm+")
                        .build());

        topic = topicRepository.save(Topic.builder().name("topic-name").build());

        post = postRepository.save(
                Post.builder()
                        .title("title")
                        .content("content")
                        .topic(topic)
                        .author(author)
                        .build());
    }

    @AfterAll
    void tearDown() {
        postRepository.deleteById(post.getId());
        topicRepository.deleteById(topic.getId());
        userRepository.deleteById(author.getId());
    }

    @Test
    void test() throws NotFoundException {
        Comment comment = Comment.builder()
                .comment("comment")
                .post(post)
                .author(author)
                .build();

        // to dto
        CommentDto commentDto = commentMapper.toDto(comment);
        assertEquals(comment.getPost().getId(), commentDto.getPostId());
        assertEquals(comment.getAuthor().getId(), commentDto.getAuthorId());
        assertEquals(comment.getAuthor().getUsername(), commentDto.getAuthorUsername());

        // to entity
        assertEquals(comment.getComment(), commentMapper.toEntity(commentDto).getComment());
        assertEquals(comment.getAuthor().getId(), commentMapper.toEntity(commentDto).getAuthor().getId());
        assertEquals(comment.getPost().getId(), commentMapper.toEntity(commentDto).getPost().getId());
    }
}
