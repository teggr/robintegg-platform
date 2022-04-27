package com.robintegg.platform.readinglist;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ReadingList {

    private List<ReadingListItem> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        items.add(new ReadingListItem("Confessions of a low code convert","https://thenewstack.io/confessions-of-a-low-code-convert/"));
    }

    public List<ReadingListItem> getItems() {
        return items;
    }

}
