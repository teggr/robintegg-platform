package com.robintegg.platform.readinglist;

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
@RequestMapping("/admin/reading-list")
@RequiredArgsConstructor
public class ReadingListItemEditorController {

    private final ReadingListItemEditor readingListItemEditor;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping("/create")
    public String getNewReadingListItemTemplate(Model model) {
        model.addAttribute("form", ReadingListItemForm.builder().build());
        return "reading-list-item-create";
    }

    @PostMapping(path = "/create", params = "create")
    public String postCreateBook(ReadingListItemForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "reading-list-item-create";
        }
        Long id = readingListItemEditor.create(form.getTitle(), form.getDate(), "", form.getSubtitle(), form.getTags(), form.getPublish());
        return "redirect:/admin/reading-list/edit/" + id;
    }

    @GetMapping("/edit/{id}")
    public String getReadingListItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("form", ReadingListItemForm.from(readingListItemEditor.getById(id)));
        return "reading-list-item-edit";
    }

    @PostMapping(path = "/edit/{id}", params = "update")
    public String postUpdateReadingListItem(@PathVariable("id") Long id, ReadingListItemForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "reading-list-item-edit";
        }
        readingListItemEditor.update(id, form.getTitle(), form.getDate(), form.getSubtitle(), form.getTags(), form.getPublish());
        return "redirect:/admin/reading-list/edit/" + id;
    }

    @PostMapping(path = "/edit/{id}", params = "delete")
    public String postDelete(@RequestParam("delete") Long id) {
        readingListItemEditor.delete(id);
        return "redirect:/admin/reading-list";
    }

}
