package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;

public interface ITopicService {

	Topic getById(long id) throws NotFoundException;

	List<Topic> getTopics();
}
