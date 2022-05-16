package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.robintegg.platform.activity.ActivityLog;
import com.robintegg.platform.activity.ActivityLogs;
import com.robintegg.platform.tags.Tag;
import com.robintegg.platform.tags.Tags;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookEditor {

    private final BookRepository bookRepository;
    private final BookPathResolver pathResolver;
    private final ActivityLogs activityLogs;
    private final Tags tags;

    public void create(String title, Instant date, String subtitle, Set<String> tags) {

        String uri = pathResolver.path(title);

        Book book = Book.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .subtitle(subtitle)
                .tags(this.tags.getTagsForNames(tags))
                .published(true)
                .build();

        Book saved = bookRepository.save(book);

        if (saved.getPublished()) {
            activityLogs.add(ActivityLog.builder()
                    .contentId(saved.getId())
                    .date(saved.getDate())
                    .event("create")
                    .type("book")
                    .tags(this.tags.getTagsForNames(tags))
                    .build());
        }

    }

    public void publish(Long id) {
        Book book = bookRepository
                .findById(id).get();
        book.setPublished(true);
        Book saved = bookRepository.save(book);

        activityLogs.add(ActivityLog.builder()
                .contentId(saved.getId())
                .date(saved.getDate())
                .event("create")
                .type("book")
                .tags(this.tags.getTagsForNames(saved.getTags().stream().map(Tag::getName).collect(Collectors.toSet())))
                .build());

    }

    public void unpublish(Long id) {

        Book book = bookRepository
                .findById(id).get();
        book.setPublished(false);
        Book saved = bookRepository.save(book);

        activityLogs.removeContent(saved.getId());

    }

    public void delete(Long id) {

        bookRepository
                .deleteById(id);

        activityLogs.removeContent(id);

    }

}
