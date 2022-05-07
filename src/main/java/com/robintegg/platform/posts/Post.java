package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String uri;
    private String title;
    private Instant date;
    private String titleImageUrl;
    @ElementCollection
    private Set<String> tags;
    @Lob
    private String content;
    private String subtitle;

}
