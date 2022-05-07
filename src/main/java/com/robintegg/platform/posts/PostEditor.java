package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostEditor {

    private final PostRepository postRepository;
    private final PostPathResolver pathResolver;

    public void create(String title, Instant date, String titleImageUrl, Set<String> tags, String content,
            String subtitle) {

        String uri = pathResolver.path(date, title);

        Post post = Post.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .titleImageUrl(titleImageUrl)
                .tags(tags)
                .content(content)
                .subtitle(subtitle)
                .build();

        postRepository.save(post);

    }

}
