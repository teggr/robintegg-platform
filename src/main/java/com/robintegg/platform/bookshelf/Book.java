package com.robintegg.platform.bookshelf;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {

    private String uri;
    private String title;
    private Instant date;

}
