package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Post;

import java.util.List;

public interface IPostService {

    Post create(Post post);

    Post getById(long id) throws NotFoundException;

    List<Post> findAllByCreatedAtDesc();
}
