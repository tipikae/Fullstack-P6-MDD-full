package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

/**
 * Topic service.
 * @author tipikae
 * @version 1.0.0
 */
@Service
public class TopicService implements ITopicService {

	@Autowired
	private TopicRepository topicRepository;

	/**
	 * Get a topic by id.
	 * @param id Topic id.
	 * @return Topic
	 * @throws NotFoundException thrown when topic is not found.
	 */
	@Override
	public Topic getById(long id) throws NotFoundException {
		Topic topic = topicRepository.findById(id).orElse(null);
		if (topic == null) {
			throw new NotFoundException(String.format("Topic with id = %d is not found.", id));
		}
		return topic;
	}

	/**
	 * Get all topics.
	 * @return List
	 */
	@Override
	public List<Topic> getTopics() {
		return topicRepository.findAll();
	}
}
