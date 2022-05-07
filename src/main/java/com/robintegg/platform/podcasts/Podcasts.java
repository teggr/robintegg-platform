package com.robintegg.platform.podcasts;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Podcasts {

    private final PodcastRepository podcastRepository;

    public List<Podcast> getItems() {
        return podcastRepository.findAll();
    }

    public Podcast getPodcast(String requestURI) {
        return podcastRepository.findByUri(requestURI);
    }

}
