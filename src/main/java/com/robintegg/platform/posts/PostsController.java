package com.robintegg.platform.posts;

import com.robintegg.platform.profile.Profile;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final Posts posts;
    
    @ModelAttribute("profile")
    public Profile getProfile() {
        return new Profile();
    }
    
    @GetMapping("/posts")
    public String getPosts(Model model, Pageable pageable) {
        model.addAttribute("posts", posts.getPosts(pageable));
        return "posts";
    }

}
