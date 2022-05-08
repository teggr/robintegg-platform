package com.robintegg.platform.activity;

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

}
