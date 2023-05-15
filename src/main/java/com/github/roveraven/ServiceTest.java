package com.github.roveraven;

import com.github.roveraven.repository.entity.Comment;
import com.github.roveraven.repository.entity.User;
import com.github.roveraven.service.CommentService;
import com.github.roveraven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ServiceTest {
    UserService userService;
    CommentService commentService;
    @Autowired
    public ServiceTest(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
        System.out.println("Hello, ServiceTest!");
        testMethod();
    }
    public void testMethod(){
        User user = new User();
        user.setName("TestUser19");
        //User user2 = userService.save(user);
        Comment comment = new Comment();
        comment.setText("testMessage33");
        comment.setUser(user);
        comment.setCommentTime(ZonedDateTime.now(ZoneId.systemDefault()));
        commentService.save(comment);
    }

}
