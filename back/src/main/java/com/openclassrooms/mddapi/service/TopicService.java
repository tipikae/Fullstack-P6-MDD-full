package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
public class TopicService implements ITopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Override
	public Topic getById(long id) throws NotFoundException {
		Topic topic = topicRepository.findById(id).orElse(null);
		if (topic == null) {
			throw new NotFoundException(String.format("Topic with id = %d is not found.", id));
		}
		return topic;
	}

	@Override
	public List<Topic> getTopics() {
		return topicRepository.findAll();
	}
}
