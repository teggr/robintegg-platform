package com.robintegg.platform.readinglist;

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
@RequestMapping("/admin/reading-list")
@RequiredArgsConstructor
public class ReadingListAdminController {

    private final ReadingListItemEditor readingListItemEditor;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping
    public String getPodcasts(Model model, Pageable pageable) {
        model.addAttribute("items", readingListItemEditor.getReadingListItems(pageable));
        return "reading-list-items-admin";
    }

    @PostMapping(params = "publish")
    public String postPublish(@RequestParam("publish") Long id) {
        readingListItemEditor.publish(id);
        return "redirect:/admin/reading-list";
    }

    @PostMapping(params = "unpublish")
    public String postUnpublish(@RequestParam("unpublish") Long id) {
        readingListItemEditor.unpublish(id);
        return "redirect:/admin/reading-list";
    }

    @PostMapping(params = "edit")
    public String postEdit(@RequestParam("edit") Long id) {
        return "redirect:/admin/reading-list/edit/" + id;
    }

    @PostMapping(params = "delete")
    public String postDelete(@RequestParam("delete") Long id) {
        readingListItemEditor.delete(id);
        return "redirect:/admin/reading-list";
    }

}
