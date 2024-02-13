package com.openclassrooms.mddapi.integration.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostMapperITest {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    private User user;
    private Topic topic;

    @BeforeAll
    void setUp() {
        user = userRepository.save(
                User.builder()
                        .username("postMapper")
                        .email("post@mapper.com")
                        .password("123456Cd+")
                        .build());

        topic = topicRepository.save(Topic.builder().name("topic-name").description("test").build());
    }

    @AfterAll
    void tearDown() {
        userRepository.deleteById(user.getId());
        topicRepository.deleteById(topic.getId());
    }

    @Test
    void test() throws NotFoundException {
        Post post = Post.builder()
                .title("title")
                .content("content")
                .user(user)
                .topic(topic)
                .build();

        // to dto
        PostDto postDto = postMapper.toDto(post);
        postDto.setUserId(user.getId());
        assertEquals(user.getId(), postDto.getUserId());
        assertEquals("postMapper", postDto.getUserName());
        assertEquals(topic.getId(), postDto.getTopicId());
        assertEquals("topic-name", postDto.getTopicName());

        // to entity
        assertEquals(postDto.getUserId(), postMapper.toEntity(postDto).getUser().getId());
        assertEquals(postDto.getTopicId(), postMapper.toEntity(postDto).getTopic().getId());
    }
}
