package com.robintegg.platform.tags;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.robintegg.platform.activity.ActivityLogs;
import com.robintegg.platform.home.ContentSummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Tags {

    private final TagRepository tagRepository;
    private final ActivityLogs activityLogs;

    public Page<Tag> getTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    public Set<Tag> getTagsForNames(Set<String> tags) {
        return tags.stream()
                .map(t -> {
                    Optional<Tag> tag = tagRepository.findByName(t);
                    if (tag.isPresent()) {
                        return tag.get();
                    } else {
                        return tagRepository.save(Tag.builder().name(t).build());
                    }
                })
                .collect(Collectors.toSet());
    }

}
