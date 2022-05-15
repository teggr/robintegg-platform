package com.robintegg.platform.activity;

import com.robintegg.platform.home.ContentSummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ActivityLogs {
    
    private final ActivityLogRepository activityLogRepository;

    public Page<ActivityLog> getLatest(Pageable pageable) {
        return activityLogRepository.findAllByOrderByDateDesc(pageable);
    }

    public void add(ActivityLog log) {
        activityLogRepository.save(log);
    }

    public Page<ActivityLog> getLatestByTag(String tagName, Pageable pageable) {
        return activityLogRepository.findAllByTagsNameContainingOrderByDateDesc(tagName, pageable);
    }

}
