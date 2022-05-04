package com.robintegg.platform.podcasts;

import org.springframework.stereotype.Component;

@Component
public class PodcastPathResolver {

    public String path(String title) {

        String path = "/podcasts/" + title + ".html";

        return path.toLowerCase().replaceAll(" ", "-");

    }

}
