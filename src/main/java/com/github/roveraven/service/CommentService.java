package com.github.roveraven.service;

import com.github.roveraven.repository.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);
    Optional<Comment> findComment(Long CommentId);
    List<Comment> findByUser(Long userId);
    List<Comment> findAll();

}
