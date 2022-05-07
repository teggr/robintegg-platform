package com.robintegg.platform;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.bookshelf.BookEditor;
import com.robintegg.platform.podcasts.PodcastEditor;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestData implements ApplicationRunner {

    private final BookEditor bookEditor;
    private final PodcastEditor podcastEditor;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        bookEditor.create("Developer Hegemony", Instant.parse("2022-05-03T13:50:00Z"), "Viva la developer revolution",
                Set.of("career"));

        podcastEditor.create("Shop Talk Show", Instant.parse("2022-05-03T13:50:00Z"),
                "https://thenewstack.io/confessions-of-a-low-code-convert/", "Web and Frontend Design", Set.of("web"));

    }

}
