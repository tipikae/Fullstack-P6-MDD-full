package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.AlreadyExistsException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public User create(User user) throws AlreadyExistsException {
        if (userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername())) {
            throw new AlreadyExistsException("Email or username is already taken.");
        }

        return userRepository.save(user);
    }

    @Override
    public User getById(long id) throws NotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException(String.format("User with id = %d is not found.", id));
        }
        return user;
    }

    @Override
    public void subscribe(long userId, long topicId) throws NotFoundException, BadRequestException {
        Topic topic = topicRepository.findById(topicId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (topic == null || user == null) {
            String message = topic == null ?
                    String.format("Topic with id = %d is not found.", topicId)
                    : String.format("User with id = %d is not found", userId);
            throw new NotFoundException(message);
        }

        boolean alreadySubscribe = user.getTopics().stream().anyMatch(t -> t.getId().equals(topicId));
        if (alreadySubscribe) {
            throw new BadRequestException("User already subscribed to this topic");
        }

        user.getTopics().add(topic);
        userRepository.save(user);
    }

    @Override
    public void unsubscribe(long userId, long topicId) throws NotFoundException, BadRequestException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException(String.format("User with id = %d is not found.", userId));
        }

        boolean alreadySubscribe = user.getTopics().stream().anyMatch(t -> t.getId().equals(topicId));
        if (!alreadySubscribe) {
            throw new BadRequestException("User not subscribed to this topic");
        }

        user.setTopics(user.getTopics().stream().filter(t -> !t.getId().equals(topicId)).collect(Collectors.toList()));
        userRepository.save(user);
    }
}
