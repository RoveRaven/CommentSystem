package com.github.roveraven.service;

import com.github.roveraven.repository.CommentRepository;
import com.github.roveraven.repository.entity.Comment;
import com.github.roveraven.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService)
    {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public Comment save(Comment comment)
    {
         User savedUser = userService.save(comment.getUser());
         comment.setCommentTime(ZonedDateTime.now(ZoneId.systemDefault()));
         comment.setUser(savedUser);
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findComment(Integer CommentId) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
