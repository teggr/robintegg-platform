package com.robintegg.platform.home;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.robintegg.platform.bookshelf.Book;
import com.robintegg.platform.bookshelf.Bookshelf;
import com.robintegg.platform.podcasts.Podcast;
import com.robintegg.platform.podcasts.Podcasts;
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
    private final Podcasts podcasts;

    public List<ContentSummary> getAll() {
        List<ContentSummary> list = new ArrayList<>();
        list.addAll(posts.getPosts().stream().map(this::mapToSummary).collect(Collectors.toList()));
        list.addAll(bookshelf.getBooks().stream().map(this::mapToSummary).collect(Collectors.toList()));
        list.addAll(readingList.getItems().stream().map(this::mapToSummary).collect(Collectors.toList()));
        list.addAll(podcasts.getItems().stream().map(this::mapToSummary).collect(Collectors.toList()));
        return list;
    }

    private ContentSummary mapToSummary(Post post) {
        return new ContentSummary() {

            @Override
            public String getTitle() {
                return post.getTitle();
            }

            @Override
            public String getSubtitle() {
                return post.getSubtitle();
            }

            @Override
            public String getLink() {
                return post.getUri();
            }

            @Override
            public List<String> getTags() {
                return post.getTags();
            }

            @Override
            public Instant getDate() {
                return post.getDate();
            }

        };
    }

    private ContentSummary mapToSummary(Book book) {
        return new ContentSummary() {

            @Override
            public String getTitle() {
                return book.getTitle();
            }

            @Override
            public String getSubtitle() {
                return book.getSubtitle();
            }

            @Override
            public String getLink() {
                return book.getUri();
            }

            @Override
            public List<String> getTags() {
                return book.getTags();
            }

            @Override
            public Instant getDate() {
                return book.getDate();
            }

        };
    }

    private ContentSummary mapToSummary(ReadingListItem readinglistitem) {
        return new ContentSummary() {

            @Override
            public String getTitle() {
                return readinglistitem.getTitle();
            }

            @Override
            public String getLink() {
                return readinglistitem.getUri();
            }

            @Override
            public String getSubtitle() {
                return readinglistitem.getSubtitle();
            }

            @Override
            public List<String> getTags() {
                return readinglistitem.getTags();
            }

            @Override
            public Instant getDate() {
                return readinglistitem.getDate();
            }

        };

    }

    private ContentSummary mapToSummary(Podcast podcast) {
        return new ContentSummary() {

            @Override
            public String getTitle() {
                return podcast.getTitle();
            }

            @Override
            public String getLink() {
                return podcast.getUri();
            }

            @Override
            public String getSubtitle() {
                return podcast.getSubtitle();
            }

            @Override
            public List<String> getTags() {
                return podcast.getTags();
            }

            @Override
            public Instant getDate() {
                return podcast.getDate();
            }

        };
    }

}
