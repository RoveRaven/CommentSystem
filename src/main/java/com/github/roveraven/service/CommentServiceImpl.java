package com.github.roveraven.service;

import com.github.roveraven.repository.CommentRepository;
import com.github.roveraven.repository.UserRepository;
import com.github.roveraven.repository.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Comment save(Comment comment) {
        if(comment.getUser().getId()==null)
            userRepository.save(comment.getUser());
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
