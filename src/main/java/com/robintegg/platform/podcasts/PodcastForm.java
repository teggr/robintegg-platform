package com.robintegg.platform.podcasts;

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
class PodcastForm {

    private Long id;
    private String title;
    private Instant date;
    private String subtitle;
    private Set<String> tags;
    private Boolean publish;

    public static PodcastForm from(Podcast podcast) {
        return new PodcastForm(
            podcast.getId(),
            podcast.getTitle(),
            podcast.getDate(),
            podcast.getSubtitle(),
            podcast.getTags().stream().map(Tag::getName).collect(Collectors.toSet()),
            podcast.isPublished()
        );
    }

}
