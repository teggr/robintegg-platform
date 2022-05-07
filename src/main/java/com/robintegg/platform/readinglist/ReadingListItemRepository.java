package com.robintegg.platform.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

interface ReadingListItemRepository extends JpaRepository<ReadingListItem,Long> {

    ReadingListItem findByUri(String uri);
    
}
