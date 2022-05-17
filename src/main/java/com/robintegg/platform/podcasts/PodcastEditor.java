package com.robintegg.platform.podcasts;

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
public class PodcastEditor {

    private final PodcastRepository podcastRepository;
    private final PodcastPathResolver pathResolver;
    private final IndexedContents indexedContents;
    private final Tags tags;

    public void create(String title, Instant date, String link, String subtitle, Set<String> tags) {

        String uri = pathResolver.path(title);

        Podcast podcast = Podcast.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .link(link)
                .subtitle(subtitle)
                .tags(this.tags.getTagsForNames(tags))
                .build();

        Podcast saved = podcastRepository.save(podcast);

        indexedContents.add(IndexedContent.builder()
                .id(new IndexedContentId("podcast", saved.getId()))
                .date(saved.getDate())
                .tags(this.tags.getTagsForNames(tags))
                .build());

    }

}
