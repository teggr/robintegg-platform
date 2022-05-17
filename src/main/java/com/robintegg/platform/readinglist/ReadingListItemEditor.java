package com.robintegg.platform.readinglist;

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
public class ReadingListItemEditor {

    private final ReadingListItemRepository readingListItemRepository;
    private final ReadingListItemPathResolver pathResolver;
    private final IndexedContents indexedContents;
    private final Tags tags;

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

        indexedContents.add(IndexedContent.builder()
                .id(new IndexedContentId("readingListItem", saved.getId()))
                .date(saved.getDate())
                .tags(this.tags.getTagsForNames(tags))
                .build());

    }

}
