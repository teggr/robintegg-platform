package com.robintegg.platform.bookshelf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookshelfController {

    private final Bookshelf bookshelf;
    
    @GetMapping("/bookshelf")
    public String getBookshelf(Model model) {
        model.addAttribute("books", bookshelf.getBooks());
        return "bookshelf";
    }

}
