package com.robintegg.platform.home;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.robintegg.platform.bookshelf.Book;
import com.robintegg.platform.bookshelf.Bookshelf;
import com.robintegg.platform.posts.Post;
import com.robintegg.platform.posts.Posts;
import com.robintegg.platform.readinglist.ReadingList;
import com.robintegg.platform.readinglist.ReadingListItem;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LatestContent {

    private final Posts posts;
    private final Bookshelf bookshelf;
    private final ReadingList readingList;

    public List<ContentSummary> getAll() {
        List<ContentSummary> list = new ArrayList<>();
        list.addAll(posts.getPosts().stream().map(LatestContent::mapToSummary).collect(Collectors.toList()));
        list.addAll(bookshelf.getBooks().stream().map(LatestContent::mapToSummary).collect(Collectors.toList()));
        list.addAll(readingList.getItems().stream().map(LatestContent::mapToSummary).collect(Collectors.toList()));
        return list;
    }

    private static ContentSummary mapToSummary(Post post) {
        return new ContentSummary() {

            @Override
            public String getTitle() {
                return post.getTitle();
            }

        };
    }

    private static ContentSummary mapToSummary(Book book) {
        return new ContentSummary() {

            @Override
            public String getTitle() {
                return book.getTitle();
            }

        };
    }

    private static ContentSummary mapToSummary(ReadingListItem readinglistitem) {
        return new ContentSummary() {

            @Override
            public String getTitle() {
                return readinglistitem.getTitle();
            }

        };

    }

}
