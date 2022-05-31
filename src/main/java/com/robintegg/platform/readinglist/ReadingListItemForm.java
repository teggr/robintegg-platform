package com.robintegg.platform.readinglist;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.robintegg.platform.tags.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ReadingListItemForm {

    private Long id;
    private String title;
    private Instant date;
    private String subtitle;
    private Set<String> tags;
    private Boolean publish;

    public static ReadingListItemForm from(ReadingListItem item) {
        return new ReadingListItemForm(
            item.getId(),
            item.getTitle(),
            item.getDate(),
            item.getSubtitle(),
            item.getTags().stream().map(Tag::getName).collect(Collectors.toSet()),
            item.isPublished()
        );
    }

}
