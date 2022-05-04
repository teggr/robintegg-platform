package com.robintegg.platform.bookshelf;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/bookshelf")
@RequiredArgsConstructor
public class BookshelfController {

    private final Bookshelf bookshelf;
    
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
