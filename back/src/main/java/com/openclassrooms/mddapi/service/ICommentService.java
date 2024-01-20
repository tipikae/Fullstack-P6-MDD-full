package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Comment;

import java.util.List;

public interface ICommentService {

    Comment addComment(Comment comment, long authorId, long postId) throws NotFoundException;

    List<Comment> getCommentsByPostId(long postId);
}
