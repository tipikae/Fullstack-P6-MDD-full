package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Post;

import java.util.List;

/**
 * Post service interface.
 * @author tipikae
 * @version 1.0.0
 */
public interface IPostService {

    /**
     * Create a post.
     * @param post Post to create.
     * @return Post
     */
    Post create(Post post);

    /**
     * Get a post by id.
     * @param id Post id.
     * @return Post
     * @throws NotFoundException thrown when te post is not found.
     */
    Post getById(long id) throws NotFoundException;

    /**
     * Get all posts ordered by created date desc.
     * @return List
     */
    List<Post> findAllByCreatedAtDesc();

    /**
     * Get all posts.
     * @param order Sort order.
     * @return List
     */
    List<Post> findAll(String order);
}
