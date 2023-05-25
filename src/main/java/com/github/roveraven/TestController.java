package com.github.roveraven;
import com.github.roveraven.repository.entity.Comment;
import com.github.roveraven.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test_page")
public class TestController {
    @Autowired
    CommentService commentService;
    @GetMapping
    public String getText() {
        StringBuilder builder = new StringBuilder();
                for(Comment c : commentService.findAll()){
                    builder.append(c+"<br>\n");
                }
        //System.out.println(builder);
        return builder.toString();
    }
}