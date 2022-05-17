package com.robintegg.platform.tags;

import com.robintegg.platform.index.IndexedContents;
import com.robintegg.platform.profile.Profile;
import com.robintegg.platform.profile.Profiles;

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
    private final IndexedContents indexedContents;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping
    public String getTags(Model model, Pageable pageable){
        model.addAttribute("tags", tags.getTags(pageable));
        return "tags";
    }

    @GetMapping("/{tagName}")
    public String getLatestTagContent( @PathVariable("tagName") String tagName, Model model, Pageable pageable){
        model.addAttribute("latest", indexedContents.getLatestByTag(tagName, pageable));
        return "tag";
    }

}
