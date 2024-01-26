package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Comment;

import java.util.List;

/**
 * Comment Repository.
 * @author tipikae
 * @version 1.0.0
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Find comments by post id.
     * @param postId Post id.
     * @return List
     */
    List<Comment> findByPostId(long postId);
}
