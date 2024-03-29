package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.AlreadyExistsException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User service.
 * @author tipikae
 * @version 1.0.0
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Create a user.
     * @param user User to create.
     * @return User
     * @throws AlreadyExistsException thrown when user already exists.
     */
    public User create(User user) throws AlreadyExistsException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("Email is already taken.");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("Username is already taken.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    /**
     * Update a user.
     * @param id User id.
     * @param user User to update.
     * @throws NotFoundException thrown when user is not found.
     * @throws AlreadyExistsException thrown when user email or username is already taken.
     */
    public void update(long id, User user) throws NotFoundException, AlreadyExistsException {
        // check if user exists
        User currentUser = userRepository.findById(id).orElse(null);
        if (currentUser == null) {
            throw new NotFoundException(String.format("User with id = %d is not found.", id));
        }

        // check if email is updated
        if (!currentUser.getEmail().equals(user.getEmail())) {
            // check if new email is already taken
            Optional<User> optional = userRepository.findByEmail(user.getEmail());
            if (optional.isPresent() && optional.get().getId() != currentUser.getId()) {
                throw new AlreadyExistsException("Email is already taken.");
            }
            currentUser.setEmail(user.getEmail());
        }

        // check if username is updated
        if (!currentUser.getUsername().equals(user.getUsername())) {
            // check if new username is already taken
            Optional<User> optional = userRepository.findByUsername(user.getUsername());
            if (optional.isPresent() && optional.get().getId() != currentUser.getId()) {
                throw new AlreadyExistsException("Username is already taken.");
            }
            currentUser.setUsername(user.getUsername());
        }

        currentUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(currentUser);
    }

    /**
     * Get a user by id.
     * @param id User id.
     * @return User
     * @throws NotFoundException thrown when user is not found.
     */
    public User getById(long id) throws NotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException(String.format("User with id = %d is not found.", id));
        }
        return user;
    }

    /**
     * Get a user by email.
     * @param email User email.
     * @return User
     * @throws NotFoundException thrown when user is not found.
     */
    public User getByEmail(String email) throws NotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new NotFoundException(String.format("User with email = %s not found.", email));
        }
        return user;
    }

    /**
     * Subscribe a user to a topic.
     * @param userId User id.
     * @param topicId Topic id.
     * @throws NotFoundException thrown when user or topic is not found.
     * @throws BadRequestException thrown when an authentication error occurred.
     */
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

    /**
     * Unsubscribe a user to a topic.
     * @param userId User id.
     * @param topicId Topic id.
     * @throws NotFoundException thrown when user or topic is not found.
     * @throws BadRequestException thrown when an authentication error occurred.
     */
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
