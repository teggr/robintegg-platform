package com.robintegg.platform.index;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.robintegg.platform.bookshelf.Book;
import com.robintegg.platform.content.ContentCreatedEvent;
import com.robintegg.platform.content.ContentDeletedEvent;
import com.robintegg.platform.content.ContentUpdatedEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class IndexedContentListener {

    private final IndexedContents indexedContents;
    private Map<Class<?>, IndexedContentFactory> typeToIndexedContentFactory;

    public IndexedContentListener(IndexedContents indexedContents,
            List<IndexedContentFactory> indexedContentFactories) {
        this.indexedContents = indexedContents;
        this.typeToIndexedContentFactory = indexedContentFactories.stream()
                .collect(Collectors.toMap(
                        f -> f.type(),
                        f -> f));
    }

    @EventListener
    public void onContentCreated(ContentCreatedEvent event) {

        IndexedContent content = typeToIndexedContentFactory
                .get(event.getSource().getClass())
                .create(event.getSource());

        indexedContents.add(content);

    }

    @EventListener
    public void onContentUpdated(ContentUpdatedEvent event) {

        IndexedContent content = typeToIndexedContentFactory
                .get(event.getSource().getClass())
                .create(event.getSource());

        indexedContents.updateContent(content);

    }

    @EventListener
    public void onContentDeleted(ContentDeletedEvent event) {

         IndexedContent content = typeToIndexedContentFactory
                .get(event.getSource().getClass())
                .create(event.getSource());

        indexedContents.removeContent(content);

    }

}
