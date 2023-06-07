package com.github.roveraven.service;

import com.github.roveraven.repository.CommentRepository;
import com.github.roveraven.repository.entity.Comment;
import com.github.roveraven.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userFromDB = userService.findByUsername(userDetails.getUsername());
        comment.setCommentTime(ZonedDateTime.now(ZoneId.systemDefault()));
        comment.setUser(userFromDB);
        comment.setImage(null);
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findComment(Long CommentId) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findByUser(Long userId) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
