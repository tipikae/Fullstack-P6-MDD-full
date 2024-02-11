package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Comment;

import java.util.List;

/**
 * Comment service interface.
 * @author tipikae
 * @version 1.0.0
 */
public interface ICommentService {

    /**
     * Add a new comment to a post.
     * @param comment Comment to add.
     * @param authorId Comment author id.
     * @param postId Post id.
     * @return Comment
     * @throws NotFoundException thrown when author or post is not found.
     */
    Comment addComment(Comment comment, long authorId, long postId) throws NotFoundException;

    /**
     * Get comments by post id.
     * @param postId Post id.
     * @return List
     */
    List<Comment> getCommentsByPostId(long postId);
}
