package com.robintegg.platform.posts;

import com.robintegg.platform.index.IndexedContent;
import com.robintegg.platform.index.IndexedContentFactory;
import com.robintegg.platform.index.IndexedContentId;

import org.springframework.stereotype.Component;

@Component
class PostIndexedContentFactory implements IndexedContentFactory {

    @Override
    public IndexedContent create(Object source) {

        Post podcast = (Post) source;

        return IndexedContent.builder()
                .id(new IndexedContentId("post", podcast.getId()))
                .date(podcast.getDate())
                .tags(podcast.getTags())
                .build();
    }

    @Override
    public Class<?> type() {
        return Post.class;
    }

}
