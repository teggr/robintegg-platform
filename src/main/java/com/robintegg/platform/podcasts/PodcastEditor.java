package com.robintegg.platform.podcasts;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.content.ContentEditor;
import com.robintegg.platform.tags.Tags;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PodcastEditor extends ContentEditor {

    private final PodcastRepository podcastRepository;
    private final PodcastPathResolver pathResolver;
    private final Tags tags;    

    public PodcastEditor(ApplicationEventPublisher eventPublisher, PodcastRepository podcastRepository,
            PodcastPathResolver pathResolver, Tags tags) {
        super(eventPublisher);
        this.podcastRepository = podcastRepository;
        this.pathResolver = pathResolver;
        this.tags = tags;
    }

    public void create(String title, Instant date, String link, String subtitle, Set<String> tags, boolean publish) {

        String uri = pathResolver.path(title);

        Podcast podcast = Podcast.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .link(link)
                .subtitle(subtitle)
                .tags(this.tags.getTagsForNames(tags))
                .published(publish)
                .build();

        Podcast saved = podcastRepository.save(podcast);

        publishCreatedEvent(saved);

    }

}
