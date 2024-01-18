package com.openclassrooms.mddapi.integration.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TopicServiceITest {

    @Autowired
    private TopicService topicService;

    @Test
    void test() throws NotFoundException {
        //get all
        assertFalse(topicService.getTopics().isEmpty());

        // get by id
        assertNotNull(topicService.getById(topicService.getTopics().get(0).getId()));
    }
}
