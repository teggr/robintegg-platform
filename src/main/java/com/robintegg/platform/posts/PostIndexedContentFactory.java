package com.robintegg.platform.posts;

import com.robintegg.platform.index.IndexedContent;
import com.robintegg.platform.index.IndexedContentFactory;
import com.robintegg.platform.index.IndexedContentId;

import org.springframework.stereotype.Component;

@Component
class PostIndexedContentFactory implements IndexedContentFactory {

    @Override
    public IndexedContent create(Object source) {

        Post post = (Post) source;

        return IndexedContent.builder()
                .id(new IndexedContentId("post", post.getId()))
                .title(post.getTitle())
                .subtitle(post.getSubtitle())
                .date(post.getDate())
                .tags(post.getTags())
                .uri(post.getUri())
                .published(post.isPublished())
                .build();
    }

    @Override
    public Class<?> type() {
        return Post.class;
    }

}
