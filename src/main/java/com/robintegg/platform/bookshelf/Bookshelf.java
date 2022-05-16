package com.robintegg.platform.bookshelf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Bookshelf {

    private final BookRepository bookRepository;
    
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBook(String uri) {
        return bookRepository.findByUri(uri);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

}
