package com.robintegg.platform.podcasts;

import java.time.Instant;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Podcast {

    private String uri;
    private String title;
    private Instant date;
    private String link;
    private String subtitle;
    private Set<String> tags;

}
