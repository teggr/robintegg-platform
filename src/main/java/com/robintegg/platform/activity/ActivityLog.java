package com.robintegg.platform.activity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.robintegg.platform.tags.Tag;

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
public class ActivityLog {

    @Id
    @GeneratedValue
    private Long id;
    private Instant date;
    private String type;
    private String event;
    private Long contentId;
    @ManyToMany
    @JoinTable(
        name="ACTIVITY_LOG_TAGS",
        joinColumns=
            @JoinColumn(name="ACTIVITY_LOG_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="TAG_ID", referencedColumnName="ID")
    )
    private Set<Tag> tags;

}
