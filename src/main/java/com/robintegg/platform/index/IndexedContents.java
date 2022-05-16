package com.robintegg.platform.index;

import java.util.Set;

import com.robintegg.platform.tags.Tag;

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

    public void add(IndexedContent log) {
        indexedContentRepository.save(log);
    }

    public Page<IndexedContent> getLatestByTag(String tagName, Pageable pageable) {
        return indexedContentRepository.findAllByTagsNameContainingOrderByDateDesc(tagName, pageable);
    }

    public void removeContent(IndexedContentId id) {
        indexedContentRepository.deleteById(id);
    }

    public void updateContent(IndexedContentId id, Set<Tag> tags) {
        IndexedContent existing = indexedContentRepository.findById(id)
                .orElse(null);

        existing.setTags(tags);

        indexedContentRepository.save(existing);

    }

}
