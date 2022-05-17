package com.robintegg.platform.index;

import com.robintegg.platform.bookshelf.Book;
import com.robintegg.platform.content.ContentCreatedEvent;
import com.robintegg.platform.content.ContentDeletedEvent;
import com.robintegg.platform.content.ContentUpdatedEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IndexedContentListener {

    private final IndexedContents indexedContents;
    private final IndexedContentFactory indexedContentFactory;

    @EventListener
    public void onContentCreated(ContentCreatedEvent event) {

        IndexedContent content = indexedContentFactory.create(event.getSource());

        indexedContents.add(content);

    }

    @EventListener
    public void onContentUpdated(ContentUpdatedEvent event) {

        Book book = (Book) event.getSource();

        indexedContents.updateContent(
                new IndexedContentId("book", book.getId()),
                book.getTags());
    }

    @EventListener
    public void onContentDeleted(ContentDeletedEvent event) {

        Book book = (Book) event.getSource();

        indexedContents.removeContent(new IndexedContentId("book", book.getId()));

    }

}
