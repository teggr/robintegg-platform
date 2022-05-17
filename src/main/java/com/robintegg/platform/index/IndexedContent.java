package com.robintegg.platform.index;

import java.time.Instant;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class IndexedContent {

    @EmbeddedId
    @GeneratedValue
    private IndexedContentId id;
    private Instant date;
    @ManyToMany
    @JoinTable(name = "INDEXED_LOG_TAGS",
     joinColumns = {
        @JoinColumn(name = "INDEXED_LOG_ID", referencedColumnName = "CONTENTID"),
        @JoinColumn(name = "INDEXED_LOG_TYPE", referencedColumnName = "CONTENTTYPE")
     } , 
     inverseJoinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID")
     )
    private Set<Tag> tags;

}
