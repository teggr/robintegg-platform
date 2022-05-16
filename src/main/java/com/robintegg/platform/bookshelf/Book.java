package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

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
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String uri;
    private String title;
    private Instant date;
    private String subtitle;
    @ManyToMany
    @JoinTable(
        name="BOOK_TAGS",
        joinColumns=
            @JoinColumn(name="BOOK_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="TAG_ID", referencedColumnName="ID")
    )
    private Set<Tag> tags;
    private Boolean published;

}
