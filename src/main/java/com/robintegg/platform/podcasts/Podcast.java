package com.robintegg.platform.podcasts;

import java.time.Instant;
import java.util.Set;

import javax.persistence.ElementCollection;
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
public class Podcast {

    @Id
    @GeneratedValue
    private Long id;
    private String uri;
    private String title;
    private Instant date;
    private String link;
    private String subtitle;
    @ElementCollection
    private Set<String> tags;

}
