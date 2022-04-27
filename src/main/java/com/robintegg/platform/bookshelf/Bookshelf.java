package com.robintegg.platform.bookshelf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class Bookshelf {

    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book("Developer Hegemony"));
    }

    public List<Book> getBooks() {
        return books;
    }

}
