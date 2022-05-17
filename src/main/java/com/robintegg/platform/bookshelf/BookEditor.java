package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.content.ContentEditor;
import com.robintegg.platform.tags.Tags;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookEditor extends ContentEditor {

    private final BookRepository bookRepository;
    private final BookPathResolver pathResolver;
    private final Tags tags;

    public BookEditor(ApplicationEventPublisher eventPublisher, BookRepository bookRepository,
            BookPathResolver bookPathResolver, Tags tags) {
        super(eventPublisher);
        this.bookRepository = bookRepository;
        this.pathResolver = bookPathResolver;
        this.tags = tags;
    }

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Long create(String title, Instant date, String subtitle, Set<String> tags, Boolean publish) {

        String uri = pathResolver.path(title);

        Book book = Book.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .subtitle(subtitle)
                .tags(this.tags.getTagsForNames(tags))
                .published(publish)
                .build();

        Book saved = bookRepository.save(book);

        publishCreatedEvent(saved);

        return book.getId();

    }

    public void update(Long id, String title, Instant date, String subtitle, Set<String> tags, boolean publish) {

        String uri = pathResolver.path(title);

        Book book = bookRepository.findById(id).orElse(null);

        book.setUri(uri);
        book.setTitle(title);
        book.setDate(date);
        book.setSubtitle(subtitle);
        book.setTags(this.tags.getTagsForNames(tags));
        book.setPublished(publish);

        Book saved = bookRepository.save(book);

        publishUpdatedEvent(saved);

    }

    public void publish(Long id) {

        Book book = bookRepository
                .findById(id).get();
        book.setPublished(true);
        Book saved = bookRepository.save(book);

        publishUpdatedEvent(saved);

    }

    public void unpublish(Long id) {

        Book book = bookRepository
                .findById(id).get();
        book.setPublished(false);
        Book saved = bookRepository.save(book);

        publishUpdatedEvent(saved);

    }

    public void delete(Long id) {

        Book book = bookRepository
                .findById(id).orElse(null);

        bookRepository.delete(book);

        publishDeletedEvent(book);

    }

}
