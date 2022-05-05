package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Bookshelf {

    private final BookPathResolver pathResolver;

    private Map<String, Book> uriToBook = new HashMap<>();

    @PostConstruct
    public void init() {
        Instant date = Instant.parse("2022-05-03T13:50:00Z");
        String title = "Developer Hegemony";
        String uri = pathResolver.path(title);
        Book book = new Book(uri, title, date, "Viva la developer revolution",List.of("career"));
        uriToBook.put(book.getUri(),book);
    }

    public List<Book> getBooks() {
        return uriToBook.values().stream().collect(Collectors.toList());
    }

    public Book getBook(String requestURI) {
        return uriToBook.get(requestURI);
    }

}
