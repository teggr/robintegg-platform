package com.robintegg.platform.readinglist;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.activity.ActivityLog;
import com.robintegg.platform.activity.ActivityLogs;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReadingListItemEditor {

    private final ReadingListItemRepository readingListItemRepository;
    private final ReadingListItemPathResolver pathResolver;
    private final ActivityLogs activityLogs;

    public void create(String title, Instant date, String link, String subtitle, Set<String> tags) {

        String uri = pathResolver.path(title);

        ReadingListItem item = ReadingListItem.builder()
                .uri(uri)
                .title(title)
                .date(date)
                .link(link)
                .subtitle(subtitle)
                .tags(tags)
                .build();

        ReadingListItem saved = readingListItemRepository.save(item);

        activityLogs.add(ActivityLog.builder()
                .contentId(saved.getId())
                .date(saved.getDate())
                .event("create")
                .type("readingListItem")
                .build());

    }

}
