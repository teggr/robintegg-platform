package com.robintegg.platform.podcasts;

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
@RequestMapping("/admin/podcasts")
@RequiredArgsConstructor
public class PodcastEditorController {

    private final PodcastEditor podcastEditor;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping("/create")
    public String getNewPodcastTemplate(Model model) {
        model.addAttribute("form", PodcastForm.builder().build());
        return "podcast-create";
    }

    @PostMapping(path = "/create", params = "create")
    public String postCreatePodcast(PodcastForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "podcast-create";
        }
        Long id = podcastEditor.create(form.getTitle(), form.getDate(), "", form.getSubtitle(), form.getTags(), form.getPublish());
        return "redirect:/admin/podcasts/edit/" + id;
    }

    @GetMapping("/edit/{id}")
    public String getPodcast(@PathVariable("id") Long id, Model model) {
        model.addAttribute("form", PodcastForm.from(podcastEditor.getById(id)));
        return "podcast-edit";
    }

    @PostMapping(path = "/edit/{id}", params = "update")
    public String postUpdatePodcast(@PathVariable("id") Long id, PodcastForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "podcast-edit";
        }
        podcastEditor.update(id, form.getTitle(), form.getDate(), form.getSubtitle(), form.getTags(), form.getPublish());
        return "redirect:/admin/podcasts/edit/" + id;
    }

    @PostMapping(path = "/edit/{id}", params = "delete")
    public String postDelete(@RequestParam("delete") Long id) {
        podcastEditor.delete(id);
        return "redirect:/admin/podcasts";
    }

}
