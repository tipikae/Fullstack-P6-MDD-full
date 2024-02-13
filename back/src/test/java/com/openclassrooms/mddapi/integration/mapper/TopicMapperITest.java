package com.openclassrooms.mddapi.integration.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TopicMapperITest {

    @Autowired
    private TopicMapper topicMapper;

    @Test
    void test() throws NotFoundException {
        Topic topic = Topic.builder()
                .name("itest-mapper-topic")
                .description("test")
                .build();

        // to dto
        TopicDto topicDto = topicMapper.toDto(topic);
        assertEquals(topic.getName(), topicDto.getName());

        // to entity
        assertEquals(topicDto.getName(), topicMapper.toEntity(topicDto).getName());
    }
}
