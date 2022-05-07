package com.robintegg.platform.podcasts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Podcasts {

    private final PodcastRepository podcastRepository;

    public Page<Podcast> getItems(Pageable pageable) {
        return podcastRepository.findAll(pageable);
    }

    public Podcast getPodcast(String uri) {
        return podcastRepository.findByUri(uri);
    }

}
