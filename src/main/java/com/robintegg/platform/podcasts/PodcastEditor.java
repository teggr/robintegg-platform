package com.robintegg.platform.podcasts;

import java.time.Instant;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PodcastEditor {

    private final PodcastRepository podcastRepository;
    private final PodcastPathResolver pathResolver;

    public void create(String title, Instant date, String link, String subtitle, Set<String> tags) {

        String uri = pathResolver.path(title);

        Podcast podcast = Podcast.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .link(link)
                .subtitle(subtitle)
                .tags(tags)
                .build();

        podcastRepository.save(podcast);

    }

}
