package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ICommentRepository;
import com.openclassrooms.mddapi.repository.IPostRepository;
import com.openclassrooms.mddapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Comment addComment(Comment comment, long authorId, long postId) throws NotFoundException {
        User user = userRepository.findById(authorId).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);
        if (user == null || post == null) {
            String message = user == null ?
                    String.format("User with id = %d is not found.", authorId)
                    : String.format("Post with id = %d is not found.", postId);
            throw new NotFoundException(message);
        }

        comment.setAuthor(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId);
    }
}
