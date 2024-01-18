package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;
}
