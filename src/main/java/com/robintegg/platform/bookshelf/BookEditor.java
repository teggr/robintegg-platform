package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.robintegg.platform.index.IndexedContent;
import com.robintegg.platform.index.IndexedContentId;
import com.robintegg.platform.index.IndexedContents;
import com.robintegg.platform.tags.Tag;
import com.robintegg.platform.tags.Tags;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookEditor {

    private final BookRepository bookRepository;
    private final BookPathResolver pathResolver;
    private final IndexedContents indexedContents;
    private final Tags tags;

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

        if (saved.getPublished()) {
            indexedContents.add(IndexedContent.builder()
                    .id(new IndexedContentId("book", saved.getId()))
                    .date(saved.getDate())
                    .event("create")
                    .tags(this.tags.getTagsForNames(tags))
                    .build());
        }

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

        if (saved.getPublished()) {
            indexedContents.updateContent(
                    new IndexedContentId("book", saved.getId()),
                    this.tags.getTagsForNames(tags));
        }

    }

    public void publish(Long id) {
        Book book = bookRepository
                .findById(id).get();
        book.setPublished(true);
        Book saved = bookRepository.save(book);

        Set<Tag> tagsForNames = this.tags
                .getTagsForNames(saved.getTags().stream().map(Tag::getName).collect(Collectors.toSet()));

        indexedContents.updateContent(
                new IndexedContentId("book", saved.getId()),
                tagsForNames);

    }

    public void unpublish(Long id) {

        Book book = bookRepository
                .findById(id).get();
        book.setPublished(false);
        Book saved = bookRepository.save(book);

        indexedContents.removeContent(new IndexedContentId("book", saved.getId()));

    }

    public void delete(Long id) {

        Book saved = bookRepository
                .findById(id).orElse(null);

        bookRepository.delete(saved);

        indexedContents.removeContent(new IndexedContentId("book", saved.getId()));

    }

}
