package com.robintegg.platform.bookshelf;

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
@RequestMapping("/admin/bookshelf/edit")
@RequiredArgsConstructor
public class BookEditorController {

    private final BookEditor bookEditor;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @GetMapping("/new")
    public String getNewBookTemplate(Model model) {
        model.addAttribute("form", BookForm.builder().build());
        model.addAttribute("action", "new");
        return "book-edit";
    }

    @PostMapping(path = "/new", params = "create")
    public String postCreateBook(BookForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "book-edit";
        }
        Long id = bookEditor.create(form.getTitle(), form.getDate(), form.getSubtitle(), form.getTags(), form.getPublish());
        return "redirect:/admin/bookshelf/edit/" + id;
    }

    @GetMapping("/{id}")
    public String getBookshelfItems(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookEditor.getById(id));
        model.addAttribute("action", "update");
        return "book-edit";
    }

    @PostMapping(path = "/{id}", params = "update")
    public String postUpdateBook(@PathVariable("id") Long id, BookForm form, BindingResult binding) {
        if (binding.hasErrors()) {
            return "book-edit";
        }
        bookEditor.update(id, form.getTitle(), form.getDate(), form.getSubtitle(), form.getTags(), form.getPublish());
        return "redirect:/admin/bookshelf/edit/" + id;
    }

    @PostMapping(path = "/{id}", params = "delete")
    public String postDelete(@RequestParam("delete") Long id) {
        bookEditor.delete(id);
        return "redirect:/admin/bookshelf";
    }

}
