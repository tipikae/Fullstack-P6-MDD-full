package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.AlreadyExistsException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;

/**
 * User service interface.
 * @author tipikae
 * @version 1.0.0
 */
public interface IUserService {

    /**
     * Create a user.
     * @param user User to create.
     * @return User
     * @throws AlreadyExistsException thrown when user already exists.
     */
    User create(User user) throws AlreadyExistsException;

    /**
     * Update a user.
     * @param id User id.
     * @param user User to update.
     * @throws NotFoundException thrown when user is not found.
     * @throws AlreadyExistsException thrown when user email or username is already taken.
     */
    void update(long id, User user) throws NotFoundException, AlreadyExistsException;

    /**
     * Get a user by id.
     * @param id User id.
     * @return User
     * @throws NotFoundException thrown when user is not found.
     */
    User getById(long id) throws NotFoundException;

    /**
     * Get a user by email.
     * @param email User email.
     * @return User
     * @throws NotFoundException thrown when user is not found.
     */
    User getByEmail(String email) throws NotFoundException;

    /**
     * Subscribe a user to a topic.
     * @param userId User id.
     * @param topicId Topic id.
     * @throws NotFoundException thrown when user or topic is not found.
     * @throws BadRequestException thrown when an authentication error occurred.
     */
    void subscribe(long userId, long topicId) throws NotFoundException, BadRequestException;

    /**
     * Unsubscribe a user to a topic.
     * @param userId User id.
     * @param topicId Topic id.
     * @throws NotFoundException thrown when user or topic is not found.
     * @throws BadRequestException thrown when an authentication error occurred.
     */
    void unsubscribe(long userId, long topicId) throws NotFoundException, BadRequestException;
}
