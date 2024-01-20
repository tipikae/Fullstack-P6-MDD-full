package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.AlreadyExistsException;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;

public interface IUserService {

    User create(User user) throws AlreadyExistsException;

    void update(long id, User user) throws NotFoundException;

    User getById(long id) throws NotFoundException;

    void subscribe(long userId, long topicId) throws NotFoundException, BadRequestException;

    void unsubscribe(long userId, long topicId) throws NotFoundException, BadRequestException;
}
