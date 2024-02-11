package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;

/**
 * Topic service interface.
 * @author tipikae
 * @version 1.0.0
 */
public interface ITopicService {

	/**
	 * Get a topic by id.
	 * @param id Topic id.
	 * @return Topic
	 * @throws NotFoundException thrown when topic is not found.
	 */
	Topic getById(long id) throws NotFoundException;

	/**
	 * Get all topics.
	 * @return List
	 */
	List<Topic> getTopics();
}
