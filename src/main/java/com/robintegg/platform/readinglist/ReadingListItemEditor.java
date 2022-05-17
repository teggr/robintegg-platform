package com.robintegg.platform.readinglist;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.content.ContentEditor;
import com.robintegg.platform.tags.Tags;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ReadingListItemEditor extends ContentEditor {

    private final ReadingListItemRepository readingListItemRepository;
    private final ReadingListItemPathResolver pathResolver;
    private final Tags tags;

    public ReadingListItemEditor(ApplicationEventPublisher eventPublisher,
            ReadingListItemRepository readingListItemRepository, ReadingListItemPathResolver pathResolver, Tags tags) {
        super(eventPublisher);
        this.readingListItemRepository = readingListItemRepository;
        this.pathResolver = pathResolver;
        this.tags = tags;
    }

    public void create(String title, Instant date, String link, String subtitle, Set<String> tags) {

        String uri = pathResolver.path(title);

        ReadingListItem item = ReadingListItem.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .link(link)
                .subtitle(subtitle)
                .tags(this.tags.getTagsForNames(tags))
                .build();

        ReadingListItem saved = readingListItemRepository.save(item);

        publishCreatedEvent(saved);

    }

}
