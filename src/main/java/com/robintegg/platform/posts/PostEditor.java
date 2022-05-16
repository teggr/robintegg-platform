package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.index.IndexedContents;
import com.robintegg.platform.index.IndexedContent;
import com.robintegg.platform.index.IndexedContentId;
import com.robintegg.platform.tags.Tags;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostEditor {

        private final PostRepository postRepository;
        private final PostPathResolver pathResolver;
        private final IndexedContents indexedContents;
        private final Tags tags;

        public void create(String title, Instant date, String titleImageUrl, Set<String> tags, String content,
                        String subtitle) {

                String uri = pathResolver.path(date, title);

                Post post = Post.builder()
                                .uri(uri)
                                .title(title)
                                .date(date)
                                .titleImageUrl(titleImageUrl)
                                .tags(this.tags.getTagsForNames(tags))
                                .content(content)
                                .subtitle(subtitle)
                                .build();

                Post saved = postRepository.save(post);

                indexedContents.add(IndexedContent.builder()
                                .id(new IndexedContentId("post", saved.getId()))
                                .date(saved.getDate())
                                .event("create")
                                .tags(this.tags.getTagsForNames(tags))
                                .build());

        }

}
