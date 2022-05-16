package com.robintegg.platform.bookshelf;

import javax.servlet.http.HttpServletRequest;

import com.robintegg.platform.profile.Profile;
import com.robintegg.platform.profile.Profiles;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/bookshelf")
@RequiredArgsConstructor
public class BookshelfController {

    private final Bookshelf bookshelf;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }
    
    @GetMapping
    public String getBookshelf(Model model, Pageable pageable) {
        model.addAttribute("books", bookshelf.getBooks(pageable));
        return "bookshelf";
    }

    @GetMapping("/**")
    public String getBook(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("book", bookshelf.getBook(httpRequest.getRequestURI()));
        return "book";
    }

}
