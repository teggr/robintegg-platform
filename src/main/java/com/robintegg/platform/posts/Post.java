package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import com.robintegg.platform.tags.Tag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String uri;
    private String title;
    private Instant date;
    private String titleImageUrl;
    @ManyToMany
    @JoinTable(
        name="POST_TAGS",
        joinColumns=
            @JoinColumn(name="POST_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="TAG_ID", referencedColumnName="ID")
    )
    private Set<Tag> tags;
    @Lob
    private String content;
    private String subtitle;
    private boolean published;

}
