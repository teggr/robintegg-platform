package com.robintegg.platform.bookshelf;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/admin/bookshelf")
@RequiredArgsConstructor
public class BookshelfAdminController {

    private final Bookshelf bookshelf;
    private final BookEditor bookEditor;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping
    public String getBookshelfItems(Model model, Pageable pageable) {
        model.addAttribute("books", bookshelf.getBooks(pageable));
        return "bookshelf-admin";
    }

    @PostMapping(params = "publish")
    public String postPublish(@RequestParam("publish") Long id) {
        bookEditor.publish(id);
        return "redirect:/admin/bookshelf";
    }

    @PostMapping(params = "unpublish")
    public String postUnpublish(@RequestParam("unpublish") Long id) {
        bookEditor.unpublish(id);
        return "redirect:/admin/bookshelf";
    }

    @PostMapping(params = "edit")
    public String postEdit(@RequestParam("edit") Long id) {
        return "redirect:/admin/bookshelf/edit/" + id;
    }

    @PostMapping(params = "delete")
    public String postDelete(@RequestParam("delete") Long id) {
        bookEditor.delete(id);
        return "redirect:/admin/bookshelf";
    }

}
