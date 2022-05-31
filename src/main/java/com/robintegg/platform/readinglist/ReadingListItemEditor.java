package com.robintegg.platform.readinglist;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.content.ContentEditor;
import com.robintegg.platform.tags.Tags;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ReadingListItem> getReadingListItems(Pageable pageable) {
        return readingListItemRepository.findAll(pageable);
    }

    public ReadingListItem getById(Long id) {
        return readingListItemRepository.findById(id).orElse(null);
}

    public Long create(String title, Instant date, String link, String subtitle, Set<String> tags, boolean publish) {

        String uri = pathResolver.path(title);

        ReadingListItem item = ReadingListItem.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .link(link)
                .subtitle(subtitle)
                .tags(this.tags.getTagsForNames(tags))
                .published(publish)
                .build();

        ReadingListItem saved = readingListItemRepository.save(item);

        publishCreatedEvent(saved);

        return saved.getId();

    }

    public void update(Long id, String title, Instant date, String subtitle, Set<String> tags, boolean publish) {

        String uri = pathResolver.path(title);

        ReadingListItem item = readingListItemRepository.findById(id).orElse(null);

        item.setUri(uri);
        item.setTitle(title);
        item.setDate(date);
        item.setSubtitle(subtitle);
        item.setTags(this.tags.getTagsForNames(tags));
        item.setPublished(publish);

        ReadingListItem saved = readingListItemRepository.save(item);

        publishUpdatedEvent(saved);

    }

    public void publish(Long id) {

        ReadingListItem item = readingListItemRepository
                .findById(id).get();
        item.setPublished(true);
        ReadingListItem saved = readingListItemRepository.save(item);

        publishUpdatedEvent(saved);

    }

    public void unpublish(Long id) {

        ReadingListItem item = readingListItemRepository
                .findById(id).get();
        item.setPublished(false);
        ReadingListItem saved = readingListItemRepository.save(item);

        publishUpdatedEvent(saved);

    }

    public void delete(Long id) {

        ReadingListItem item = readingListItemRepository
                .findById(id).orElse(null);

        readingListItemRepository.delete(item);

        publishDeletedEvent(item);

    }

}
