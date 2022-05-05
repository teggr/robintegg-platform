package com.robintegg.platform.podcasts;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Podcasts {

    private final PodcastPathResolver pathResolver;

    private Map<String,Podcast> uriToPodcast = new HashMap<>();

    @PostConstruct
    public void init() {
        Instant date = Instant.parse("2022-05-03T13:50:00Z");
        String title = "Shop Talk Show";
        String uri = pathResolver.path(title);
        String link = "https://thenewstack.io/confessions-of-a-low-code-convert/";
        Podcast podcast = new Podcast(uri, title,date, link);
        uriToPodcast.put(podcast.getUri(),podcast);
    }

    public List<Podcast> getItems() {
        return uriToPodcast.values().stream().toList();
    }

    public Podcast getPodcast(String requestURI) {
        return uriToPodcast.get(requestURI);
    }

}
