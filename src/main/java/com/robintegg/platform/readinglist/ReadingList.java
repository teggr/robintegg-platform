package com.robintegg.platform.readinglist;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReadingList {

    private final ReadingListItemPathResolver pathResolver;

    private Map<String,ReadingListItem> uriToItem = new HashMap<>();

    @PostConstruct
    public void init() {
        Instant date = Instant.parse("2022-05-03T13:50:00Z");
        String title = "Confessions of a low code convert";
        String uri = pathResolver.path(title);
        String link = "https://thenewstack.io/confessions-of-a-low-code-convert/";
        ReadingListItem readingListItem = new ReadingListItem(uri, title,date, link);
        uriToItem.put(readingListItem.getUri(),readingListItem);
    }

    public List<ReadingListItem> getItems() {
        return uriToItem.values().stream().toList();
    }

    public ReadingListItem getItem(String requestURI) {
        return uriToItem.get(requestURI);
    }

}
