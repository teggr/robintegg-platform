package com.robintegg.platform.readinglist;

import com.robintegg.platform.index.IndexedContent;
import com.robintegg.platform.index.IndexedContentFactory;
import com.robintegg.platform.index.IndexedContentId;

import org.springframework.stereotype.Component;

@Component
class ReadingListItemIndexedContentFactory implements IndexedContentFactory {

    @Override
    public IndexedContent create(Object source) {

        ReadingListItem podcast = (ReadingListItem) source;

        return IndexedContent.builder()
                .id(new IndexedContentId("readingListItem", podcast.getId()))
                .date(podcast.getDate())
                .tags(podcast.getTags())
                .build();
    }

    @Override
    public Class<?> type() {
        return ReadingListItem.class;
    }

}
