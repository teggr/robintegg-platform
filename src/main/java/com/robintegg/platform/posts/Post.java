package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Post {

    private String uri;
    private String title;
    private Instant date;
    private String titleImageUrl;
    private Set<String> tags;
    private String content;
    private String subtitle;

}
