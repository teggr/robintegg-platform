package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.activity.ActivityLog;
import com.robintegg.platform.activity.ActivityLogs;
import com.robintegg.platform.tags.Tags;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostEditor {

    private final PostRepository postRepository;
    private final PostPathResolver pathResolver;
    private final ActivityLogs activityLogs;
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

        activityLogs.add(ActivityLog.builder()
                .contentId(saved.getId())
                .date(saved.getDate())
                .event("create")
                .type("post")
                .build());

    }

}
