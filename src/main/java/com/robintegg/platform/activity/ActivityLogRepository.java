package com.robintegg.platform.activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

interface ActivityLogRepository extends JpaRepository<ActivityLog,Long> {

    Page<ActivityLog> findAllByOrderByDateDesc(Pageable pageable);

    Page<ActivityLog> findAllByTagsNameContainingOrderByDateDesc(String tagName, Pageable pageable);

    @Modifying
    void deleteAllByContentId(Long id);

}
