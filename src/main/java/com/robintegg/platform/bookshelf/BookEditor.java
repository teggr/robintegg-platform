package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.activity.ActivityLog;
import com.robintegg.platform.activity.ActivityLogs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookEditor {

    private final BookRepository bookRepository;
    private final BookPathResolver pathResolver;
    private final ActivityLogs activityLogs;

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

        Book saved = bookRepository.save(book);

        activityLogs.add(ActivityLog.builder()
                .contentId(saved.getId())
                .date(saved.getDate())
                .event("create")
                .type("book")
                .build());

    }

}
