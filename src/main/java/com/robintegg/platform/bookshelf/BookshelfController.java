package com.robintegg.platform.bookshelf;

import javax.servlet.http.HttpServletRequest;

import com.robintegg.platform.profile.Profile;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/bookshelf")
@RequiredArgsConstructor
public class BookshelfController {

    private final Bookshelf bookshelf;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return new Profile();
    }
    
    @GetMapping
    public String getBookshelf(Model model) {
        model.addAttribute("books", bookshelf.getBooks());
        return "bookshelf";
    }

    @GetMapping("/**")
    public String getBook(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("book", bookshelf.getBook(httpRequest.getRequestURI()));
        return "book";
    }

}
