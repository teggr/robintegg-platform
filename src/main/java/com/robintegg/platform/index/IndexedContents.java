package com.robintegg.platform.index;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class IndexedContents {

    private final IndexedContentRepository indexedContentRepository;

    public Page<IndexedContent> getLatest(Pageable pageable) {
        return indexedContentRepository.findAllByOrderByDateDesc(pageable);
    }

    public void add(IndexedContent content) {
        indexedContentRepository.save(content);
    }

    public Page<IndexedContent> getLatestByTag(String tagName, Pageable pageable) {
        return indexedContentRepository.findAllByTagsNameContainingOrderByDateDesc(tagName, pageable);
    }

    public void removeContent(IndexedContent content) {
        indexedContentRepository.deleteById(content.getId());
    }

    public void updateContent(IndexedContent content) {
        indexedContentRepository.save(content);
    }

}
