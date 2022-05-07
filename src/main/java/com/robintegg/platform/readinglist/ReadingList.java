package com.robintegg.platform.readinglist;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReadingList {

    private final ReadingListItemRepository readingListItemRepository;

    public List<ReadingListItem> getItems() {
        return readingListItemRepository.findAll();
    }

    public ReadingListItem getItem(String requestURI) {
        return readingListItemRepository.findByUri(requestURI);
    }

}
