package com.robintegg.platform.posts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final Posts posts;
    
    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", posts.getPosts());
        return "posts";
    }

}
