package com.robintegg.platform.readinglist;

import java.time.Instant;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.robintegg.platform.tags.Tag;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ReadingListItem {

    @Id
    @GeneratedValue
    private Long id;
    private String uri;
    private String title;
    private Instant date;
    private String link;
    private String subtitle;
    @ManyToMany
    @JoinTable(
        name="READING_LIST_ITEM_TAGS",
        joinColumns=
            @JoinColumn(name="READING_LIST_ITEM_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="TAG_ID", referencedColumnName="ID")
    )
    private Set<Tag> tags;

}
