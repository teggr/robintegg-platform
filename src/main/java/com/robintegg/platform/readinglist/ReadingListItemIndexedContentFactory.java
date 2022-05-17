package com.robintegg.platform.readinglist;

import com.robintegg.platform.index.IndexedContent;
import com.robintegg.platform.index.IndexedContentFactory;
import com.robintegg.platform.index.IndexedContentId;

import org.springframework.stereotype.Component;

@Component
class ReadingListItemIndexedContentFactory implements IndexedContentFactory {

    @Override
    public IndexedContent create(Object source) {

        ReadingListItem item = (ReadingListItem) source;

        return IndexedContent.builder()
                .id(new IndexedContentId("readingListItem", item.getId()))
                .title(item.getTitle())
                .subtitle(item.getSubtitle())
                .date(item.getDate())
                .tags(item.getTags())
                .uri(item.getUri())
                .published(item.isPublished())
                .build();
    }

    @Override
    public Class<?> type() {
        return ReadingListItem.class;
    }

}
