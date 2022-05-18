package com.robintegg.platform.podcasts;

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
@RequestMapping("/admin/podcasts")
@RequiredArgsConstructor
public class PodcastsAdminController {

    private final PodcastEditor podcastEditor;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping
    public String getPodcasts(Model model, Pageable pageable) {
        model.addAttribute("podcasts", podcastEditor.getPodcasts(pageable));
        return "podcasts-admin";
    }

    @PostMapping(params = "publish")
    public String postPublish(@RequestParam("publish") Long id) {
        podcastEditor.publish(id);
        return "redirect:/admin/podcasts";
    }

    @PostMapping(params = "unpublish")
    public String postUnpublish(@RequestParam("unpublish") Long id) {
        podcastEditor.unpublish(id);
        return "redirect:/admin/podcasts";
    }

    @PostMapping(params = "edit")
    public String postEdit(@RequestParam("edit") Long id) {
        return "redirect:/admin/podcasts/edit/" + id;
    }

    @PostMapping(params = "delete")
    public String postDelete(@RequestParam("delete") Long id) {
        podcastEditor.delete(id);
        return "redirect:/admin/podcasts";
    }

}
