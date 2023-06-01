package com.github.roveraven;

import com.github.roveraven.repository.RoleRepository;
import com.github.roveraven.repository.entity.Comment;
import com.github.roveraven.repository.entity.Role;
import com.github.roveraven.repository.entity.User;
import com.github.roveraven.service.CommentService;
import com.github.roveraven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestService {
    UserService userService;
    RoleRepository roleRepository;
    CommentService commentService;
    @Autowired
    public TestService(UserService userService, CommentService commentService, RoleRepository roleRepository) {
        this.userService = userService;
        this.commentService = commentService;
        this.roleRepository = roleRepository;
        System.out.println("Hello, ServiceTest!");
        //testMethod();
    }
    public void testMethod(){
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        //roleRepository.save(role);
        User user = new User();
        user.setUsername("TestUser19");
        //User user2 = userService.save(user);
        Comment comment = new Comment();
        comment.setText("""
По умолчанию текст на веб-страницах выровнен по левому краю элемента, в котором он располагается, однако используя свойство text-align, можно переопределить, как будут выравниваться строки текста относительно границ элемента. Рассмотрим каждое из возможных значений:

left - выравнивает текст по левому краю.
right - выравнивает текст по правому краю.
center - выравнивает текст по центру.
justify - выравнивает текст по ширине, в таком тексте оба конца строки размещаются вплотную к внутренним краям элемента. Пробелы между словами в этом случае корректируются браузером так, что бы длина всех строк была строго одинаковая.""");
        comment.setUser(user);
        //commentService.save(comment);
    }

}
