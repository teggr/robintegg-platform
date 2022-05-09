package com.robintegg.platform.tags;

import com.robintegg.platform.profile.Profile;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagsController {

    private final Tags tags;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return new Profile();
    }

    @GetMapping
    public String getTags(Model model, Pageable pageable){
        model.addAttribute("tags", tags.getTags(pageable));
        return "tags";
    }

    @GetMapping("/{tagName}")
    public String getTagContent( @PathVariable("tagName") String tagName, Model model, Pageable pageable){
       // model.addAttribute("tags", tags.getTagContent(tagName, pageable));
        return "tags";
    }

}
