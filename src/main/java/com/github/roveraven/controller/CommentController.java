package com.github.roveraven.controller;

import com.github.roveraven.repository.entity.Comment;
import com.github.roveraven.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String addCommentView(Model model)
    {
        return "index";
    }

    @GetMapping("/comments")
    public String viewComments(Model model)
    {
        model.addAttribute("comments", commentService.findAll()
                .stream()
                .sorted((t1, t2)-> t2.getCommentTime().compareTo(t1.getCommentTime()))
                .collect(Collectors.toList()));
        model.addAttribute("newcomment", new Comment());
        return "comments";
    }

    @PostMapping("/comments")
    public RedirectView addComment(@ModelAttribute("newcomment") Comment comment, RedirectAttributes redirectAttributes)
    {
        final RedirectView redirectView = new RedirectView("/comments", true);
        Comment savedComment = commentService.save(comment);
        redirectAttributes.addFlashAttribute("savedComment", savedComment);
        redirectAttributes.addFlashAttribute("addCommentSuccess", true);
        return redirectView;
    }
    @GetMapping("/test")
    public String testpage(Model model) {
        return "test";
    }
}
