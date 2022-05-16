package com.robintegg.platform.bookshelf;

import java.time.Instant;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookForm {

    private String title;
    private Instant date;
    private String subtitle;
    private Set<String> tags;
    private Boolean publish;

}
