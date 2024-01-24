package com.openclassrooms.mddapi.integration.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.ITopicRepository;
import com.openclassrooms.mddapi.service.ITopicService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TopicServiceITest {

    @Autowired
    private ITopicService topicService;

    @Autowired
    private ITopicRepository topicRepository;

    private Topic topic;

    @BeforeAll
    void setUp() {
        topic = topicRepository.save(Topic.builder().name("itest-topic").build());
    }

    @AfterAll
    void tearDown() {
        topicRepository.deleteById(topic.getId());
    }

    @Test
    void test() throws NotFoundException {
        //get all
        assertEquals(1, topicService.getTopics().size());

        // get by id
        assertNotNull(topicService.getById(topic.getId()));
    }
}
