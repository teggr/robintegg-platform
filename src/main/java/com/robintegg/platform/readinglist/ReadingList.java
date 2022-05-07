package com.robintegg.platform.readinglist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReadingList {

    private final ReadingListItemRepository readingListItemRepository;

    public Page<ReadingListItem> getItems(Pageable pageable) {
        return readingListItemRepository.findAll(pageable);
    }

    public ReadingListItem getItem(String uri) {
        return readingListItemRepository.findByUri(uri);
    }

}
