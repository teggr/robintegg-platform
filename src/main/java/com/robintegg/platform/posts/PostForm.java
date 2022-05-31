package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.robintegg.platform.tags.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class PostForm {

    private Long id;
    private String title;
    private Instant date;
    private String subtitle;
    private Set<String> tags;
    private Boolean publish;

    public static PostForm from(Post post) {
        return new PostForm(
            post.getId(),
            post.getTitle(),
            post.getDate(),
            post.getSubtitle(),
            post.getTags().stream().map(Tag::getName).collect(Collectors.toSet()),
            post.isPublished()
        );
    }

}
