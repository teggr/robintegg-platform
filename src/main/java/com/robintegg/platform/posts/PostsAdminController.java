package com.robintegg.platform.posts;

import com.robintegg.platform.profile.Profile;
import com.robintegg.platform.profile.Profiles;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/posts")
@RequiredArgsConstructor
public class PostsAdminController {

    private final PostEditor postEditor;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping
    public String getPodcasts(Model model, Pageable pageable) {
        model.addAttribute("posts", postEditor.getPosts(pageable));
        return "posts-admin";
    }

    @PostMapping(params = "publish")
    public String postPublish(@RequestParam("publish") Long id) {
        postEditor.publish(id);
        return "redirect:/admin/posts";
    }

    @PostMapping(params = "unpublish")
    public String postUnpublish(@RequestParam("unpublish") Long id) {
        postEditor.unpublish(id);
        return "redirect:/admin/posts";
    }

    @PostMapping(params = "edit")
    public String postEdit(@RequestParam("edit") Long id) {
        return "redirect:/admin/posts/edit/" + id;
    }

    @PostMapping(params = "delete")
    public String postDelete(@RequestParam("delete") Long id) {
        postEditor.delete(id);
        return "redirect:/admin/posts";
    }

}
