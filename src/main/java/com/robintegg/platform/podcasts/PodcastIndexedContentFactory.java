package com.robintegg.platform.podcasts;

import com.robintegg.platform.index.IndexedContent;
import com.robintegg.platform.index.IndexedContentFactory;
import com.robintegg.platform.index.IndexedContentId;

import org.springframework.stereotype.Component;

@Component
class PodcastIndexedContentFactory implements IndexedContentFactory {

    @Override
    public IndexedContent create(Object source) {

        Podcast podcast = (Podcast) source;

        return IndexedContent.builder()
                .id(new IndexedContentId("podcast", podcast.getId()))
                .date(podcast.getDate())
                .tags(podcast.getTags())
                .build();
    }

    @Override
    public Class<?> type() {
        return Podcast.class;
    }

}
