package com.openclassrooms.mddapi.integration.repository;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TopicRepositoryITest {

    @Autowired
    TopicRepository topicRepository;

    @Test
    void test() {
        // save
        Topic topic = topicRepository.save(Topic.builder().name("topic-name").description("test").build());
        assertNotNull(topic);

        // get all
        assertFalse(topicRepository.findAll().isEmpty());

        // get one by id
        assertTrue(topicRepository.existsById(topic.getId()));

        // delete
        topicRepository.deleteById(topic.getId());
        assertFalse(topicRepository.existsById(topic.getId()));
    }
}
