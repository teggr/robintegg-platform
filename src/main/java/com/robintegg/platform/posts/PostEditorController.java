package com.robintegg.platform.posts;

import com.robintegg.platform.profile.Profile;
import com.robintegg.platform.profile.Profiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/posts")
@RequiredArgsConstructor
public class PostEditorController {

    private final PostEditor postEditor;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping("/create")
    public String getNewBookTemplate(Model model) {
        model.addAttribute("form", PostForm.builder().build());
        return "post-create";
    }

    @PostMapping(path = "/create", params = "create")
    public String postCreatePost(PostForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "post-create";
        }
        Long id = postEditor.create(form.getTitle(), form.getDate(), "", form.getTags(),"", form.getSubtitle(), form.getPublish());
        return "redirect:/admin/posts/edit/" + id;
    }

    @GetMapping("/edit/{id}")
    public String getPostItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("form", PostForm.from(postEditor.getById(id)));
        return "post-edit";
    }

    @PostMapping(path = "/edit/{id}", params = "update")
    public String postUpdatePost(@PathVariable("id") Long id, PostForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "post-edit";
        }
        postEditor.update(id, form.getTitle(), form.getDate(), form.getSubtitle(), form.getTags(), form.getPublish());
        return "redirect:/admin/posts/edit/" + id;
    }

    @PostMapping(path = "/edit/{id}", params = "delete")
    public String postDelete(@RequestParam("delete") Long id) {
        postEditor.delete(id);
        return "redirect:/admin/posts";
    }

}
