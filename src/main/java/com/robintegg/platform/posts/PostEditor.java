package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.content.ContentEditor;
import com.robintegg.platform.tags.Tags;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        public Page<Post> getPosts(Pageable pageable) {
                return postRepository.findAll(pageable);
        }

        public Post getById(Long id) {
                return postRepository.findById(id).orElse(null);
        }

        public Long create(String title, Instant date, String titleImageUrl, Set<String> tags, String content,
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

                return saved.getId();

        }

        public void update(Long id, String title, Instant date, String subtitle, Set<String> tags, boolean publish) {

                String uri = pathResolver.path(date, title);

                Post post = postRepository.findById(id).orElse(null);

                post.setUri(uri);
                post.setTitle(title);
                post.setDate(date);
                post.setSubtitle(subtitle);
                post.setTags(this.tags.getTagsForNames(tags));
                post.setPublished(publish);

                Post saved = postRepository.save(post);

                publishUpdatedEvent(saved);

        }

        public void publish(Long id) {

                Post post = postRepository
                                .findById(id).get();
                post.setPublished(true);
                Post saved = postRepository.save(post);

                publishUpdatedEvent(saved);

        }

        public void unpublish(Long id) {

                Post post = postRepository
                                .findById(id).get();
                post.setPublished(false);
                Post saved = postRepository.save(post);

                publishUpdatedEvent(saved);

        }

        public void delete(Long id) {

                Post post = postRepository
                                .findById(id).orElse(null);

                postRepository.delete(post);

                publishDeletedEvent(post);

        }

}
