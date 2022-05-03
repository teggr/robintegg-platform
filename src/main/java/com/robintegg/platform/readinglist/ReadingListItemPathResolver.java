package com.robintegg.platform.readinglist;

import org.springframework.stereotype.Component;

@Component
public class ReadingListItemPathResolver {

    public String path(String title) {

        String path = "/" + title + ".html";

        return path.toLowerCase().replaceAll(" ", "-");

    }

}
