package com.robintegg.platform.home;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.robintegg.platform.bookshelf.Book;
import com.robintegg.platform.bookshelf.Bookshelf;
import com.robintegg.platform.index.IndexedContents;
import com.robintegg.platform.podcasts.Podcast;
import com.robintegg.platform.podcasts.Podcasts;
import com.robintegg.platform.posts.Post;
import com.robintegg.platform.posts.Posts;
import com.robintegg.platform.readinglist.ReadingList;
import com.robintegg.platform.readinglist.ReadingListItem;
import com.robintegg.platform.tags.Tag;

import org.springframework.data.domain.Page;
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
    private final IndexedContents activityLogs;

    public Page<ContentSummary> getAll(Pageable pageable) {

        // TODO: move mapping out of here to specific content handlers

        return activityLogs
            .getLatest(pageable)
            .map(l -> {
                if("post".equals(l.getId().getContentType())) {
                    return mapToSummary(posts.getById(l.getId().getContentId()));
                }
                if("book".equals(l.getId().getContentType())) {
                    return mapToSummary(bookshelf.getById(l.getId().getContentId()));
                }
                if("readingListItem".equals(l.getId().getContentType())) {
                    return mapToSummary(readingList.getById(l.getId().getContentId()));
                }
                if("podcast".equals(l.getId().getContentType())) {
                    return mapToSummary(podcasts.getById(l.getId().getContentId()));
                }
                return null;
            });
        
    }
    

    public Object getAllByTag(String tagName, Pageable pageable) {
    
        // TODO: move mapping out of here to specific content handlers

        return activityLogs
            .getLatestByTag(tagName, pageable)
            .map(l -> {
                if("post".equals(l.getId().getContentType())) {
                    return mapToSummary(posts.getById(l.getId().getContentId()));
                }
                if("book".equals(l.getId().getContentType())) {
                    return mapToSummary(bookshelf.getById(l.getId().getContentId()));
                }
                if("readingListItem".equals(l.getId().getContentType())) {
                    return mapToSummary(readingList.getById(l.getId().getContentId()));
                }
                if("podcast".equals(l.getId().getContentType())) {
                    return mapToSummary(podcasts.getById(l.getId().getContentId()));
                }
                return null;
            });

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
                return post.getTags().stream().map(Tag::getName).collect(Collectors.toSet());
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
                return book.getTags().stream().map(Tag::getName).collect(Collectors.toSet());
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
                return readinglistitem.getTags().stream().map(Tag::getName).collect(Collectors.toSet());
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
                return podcast.getTags().stream().map(Tag::getName).collect(Collectors.toSet());
            }

            @Override
            public Instant getDate() {
                return podcast.getDate();
            }

        };
    }

}
