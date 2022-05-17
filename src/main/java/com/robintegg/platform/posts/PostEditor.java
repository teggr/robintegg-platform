package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.content.ContentEditor;
import com.robintegg.platform.tags.Tags;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PostEditor extends ContentEditor {

        private final PostRepository postRepository;
        private final PostPathResolver pathResolver;
        private final Tags tags;

        public PostEditor(ApplicationEventPublisher eventPublisher, PostRepository postRepository,
                        PostPathResolver pathResolver, Tags tags) {
                super(eventPublisher);
                this.postRepository = postRepository;
                this.pathResolver = pathResolver;
                this.tags = tags;
        }

        public void create(String title, Instant date, String titleImageUrl, Set<String> tags, String content,
                        String subtitle, boolean publish) {

                String uri = pathResolver.path(date, title);

                Post post = Post.builder()
                                .uri(uri)
                                .title(title)
                                .date(date)
                                .titleImageUrl(titleImageUrl)
                                .tags(this.tags.getTagsForNames(tags))
                                .content(content)
                                .subtitle(subtitle)
                                .published(publish)
                                .build();

                Post saved = postRepository.save(post);

                publishCreatedEvent(saved);

        }

}
