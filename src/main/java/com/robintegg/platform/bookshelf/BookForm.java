package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.robintegg.platform.tags.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookForm {

    private Long id;
    private String title;
    private Instant date;
    private String subtitle;
    private Set<String> tags;
    private Boolean publish;

    public static BookForm from(Book book) {
        return new BookForm(
            book.getId(),
            book.getTitle(),
            book.getDate(),
            book.getSubtitle(),
            book.getTags().stream().map(Tag::getName).collect(Collectors.toSet()),
            book.isPublished()
        );
    }

}
