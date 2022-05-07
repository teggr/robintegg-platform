package com.robintegg.platform.bookshelf;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Bookshelf {

    private final BookRepository bookRepository;


    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(String requestURI) {
        return bookRepository.findByUri(requestURI);
    }

}
