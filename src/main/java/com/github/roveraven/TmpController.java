package com.github.roveraven;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test_page")
public class TmpController {

    @GetMapping
    public String getText() {
        return "Hello, Web!";
    }
}