package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {

    private String uri;
    private String title;
    private Instant date;
    private String subtitle;
    private List<String> tags;

}
