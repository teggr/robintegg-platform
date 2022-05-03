package com.robintegg.platform.readinglist;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReadingListItem {

    private String uri;
    private String title;
    private Instant date;
    private String link;

}
