package com.robintegg.platform.bookshelf;

import com.robintegg.platform.index.IndexedContent;
import com.robintegg.platform.index.IndexedContentFactory;
import com.robintegg.platform.index.IndexedContentId;

import org.springframework.stereotype.Component;

@Component
class BookIndexedContentFactory implements IndexedContentFactory {

    @Override
    public IndexedContent create(Object source) {

        Book book = (Book) source;

        return IndexedContent.builder()
                .id(new IndexedContentId("book", book.getId()))
                .title(book.getTitle())
                .subtitle(book.getSubtitle())
                .date(book.getDate())
                .tags(book.getTags())
                .uri(book.getUri())
                .published(book.isPublished())
                .build();
    }

    @Override
    public Class<?> type() {
        return Book.class;
    }

}
