package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Comment;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);
}
