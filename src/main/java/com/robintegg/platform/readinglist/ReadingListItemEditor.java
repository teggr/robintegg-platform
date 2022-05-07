package com.robintegg.platform.readinglist;

import java.time.Instant;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReadingListItemEditor {

    private final ReadingListItemRepository readingListItemRepository;
    private final ReadingListItemPathResolver pathResolver;

    public void create( String title, Instant date,String link,String subtitle,Set<String> tags)  {

        String uri = pathResolver.path(title);

        ReadingListItem item = ReadingListItem.builder()
        .uri(uri)
        .title(title)
        .date(date)
        .link(link)
        .subtitle(subtitle)
        .tags(tags)
        .build();

        readingListItemRepository.save(item);

    }

}
