package com.robintegg.platform.bookshelf;

import org.springframework.stereotype.Component;

@Component
public class BookPathResolver {

    public String path(String title) {

        String path = "/bookshelf/" + title + ".html";

        return path.toLowerCase().replaceAll(" ", "-");

    }

}
