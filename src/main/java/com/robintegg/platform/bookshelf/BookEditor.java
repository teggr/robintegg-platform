package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookEditor {

    private final BookRepository bookRepository;
    private final BookPathResolver pathResolver;

    @Transactional
    public void create(String title, Instant date, String subtitle, Set<String> tags) {

        String uri = pathResolver.path(title);

        Book book = Book.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .subtitle(subtitle)
                .tags(tags)
                .build();

        bookRepository.save(book);

    }

}
