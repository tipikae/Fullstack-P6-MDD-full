package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Comment service implementation.
 * @author tipikae
 * @version 1.0.0
 */
@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Add a new comment to a post.
     * @param comment Comment to add.
     * @param authorId Comment author id.
     * @param postId Post id.
     * @return Comment
     * @throws NotFoundException thrown when author or post is not found.
     */
    public Comment addComment(Comment comment, long authorId, long postId) throws NotFoundException {
        User user = userRepository.findById(authorId).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);
        if (user == null || post == null) {
            String message = user == null ?
                    String.format("User with id = %d is not found.", authorId)
                    : String.format("Post with id = %d is not found.", postId);
            throw new NotFoundException(message);
        }

        return commentRepository.save(
                Comment.builder()
                        .comment(comment.getComment())
                        .user(user)
                        .post(post)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }

    /**
     * Get comments by post id.
     * @param postId Post id.
     * @return List
     */
    public List<Comment> getCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId);
    }
}
