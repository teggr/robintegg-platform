package com.robintegg.platform.home;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.robintegg.platform.bookshelf.Book;
import com.robintegg.platform.bookshelf.Bookshelf;
import com.robintegg.platform.podcasts.Podcast;
import com.robintegg.platform.podcasts.Podcasts;
import com.robintegg.platform.posts.Post;
import com.robintegg.platform.posts.Posts;
import com.robintegg.platform.readinglist.ReadingList;
import com.robintegg.platform.readinglist.ReadingListItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LatestContent {

    private final Posts posts;
    private final Bookshelf bookshelf;
    private final ReadingList readingList;
    private final Podcasts podcasts;

    public Page<ContentSummary> getAll(Pageable pageable) {
        List<ContentSummary> list = new ArrayList<>();
        list.addAll(posts.getPosts(pageable).stream().map(this::mapToSummary).collect(Collectors.toList()));
        list.addAll(bookshelf.getBooks(pageable).stream().map(this::mapToSummary).collect(Collectors.toList()));
        list.addAll(readingList.getItems(pageable).stream().map(this::mapToSummary).collect(Collectors.toList()));
        list.addAll(podcasts.getItems(pageable).stream().map(this::mapToSummary).collect(Collectors.toList()));
        return new PageImpl<>(list,pageable,0   );
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
            public Set<String> getTags() {
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
            public Set<String> getTags() {
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
            public Set<String> getTags() {
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
            public Set<String> getTags() {
                return podcast.getTags();
            }

            @Override
            public Instant getDate() {
                return podcast.getDate();
            }

        };
    }

}
