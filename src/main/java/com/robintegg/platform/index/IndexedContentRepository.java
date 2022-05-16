package com.robintegg.platform.index;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface IndexedContentRepository extends JpaRepository<IndexedContent,IndexedContentId> {

    Page<IndexedContent> findAllByOrderByDateDesc(Pageable pageable);

    Page<IndexedContent> findAllByTagsNameContainingOrderByDateDesc(String tagName, Pageable pageable);

}
