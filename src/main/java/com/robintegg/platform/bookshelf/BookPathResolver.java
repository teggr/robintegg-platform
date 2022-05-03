package com.robintegg.platform.bookshelf;

import org.springframework.stereotype.Component;

@Component
public class BookPathResolver {

    public String path(String title) {

        String path = "/" + title + ".html";

        return path.toLowerCase().replaceAll(" ", "-");

    }

}
