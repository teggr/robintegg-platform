package com.robintegg.platform;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.bookshelf.BookEditor;
import com.robintegg.platform.podcasts.PodcastEditor;
import com.robintegg.platform.posts.PostEditor;
import com.robintegg.platform.readinglist.ReadingListItemEditor;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestData implements ApplicationRunner {

        private final BookEditor bookEditor;
        private final PodcastEditor podcastEditor;
        private final PostEditor postEditor;
        private final ReadingListItemEditor readingListItemEditor;

        @Override
        public void run(ApplicationArguments args) throws Exception {

                bookEditor.create("Developer Hegemony", Instant.parse("2022-05-03T13:50:00Z"),
                                "Viva la developer revolution",
                                Set.of("career"));

                podcastEditor.create("Shop Talk Show", Instant.parse("2022-05-03T13:50:00Z"),
                                "https://thenewstack.io/confessions-of-a-low-code-convert/", "Web and Frontend Design",
                                Set.of("web"));

                postEditor.create(
                                "First look at Java support in Visual Studio Code",
                                Instant.parse("2022-05-03T13:50:00Z"),
                                "/assets/images/ben-kolde-bs2Ba7t69mM-unsplash.jpg",
                                Set.of("visual studio", "java", "tools", "ide", "visual studio plugins"),
                                "Microsoft recently released a [Visual Studio Java extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) and a [Java Test Runner](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test) to the Visual Studio Code market place. The [Visual Studio Java extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) adds debugging support to the [Red Hat language support for Java](https://marketplace.visualstudio.com/items?itemName=redhat.java). The [Java Test Runner](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test) adds support for executing JUnit tests.",
                                "Java in Visual Studio");

                postEditor.create("Deploying a PlantUML Spring Boot application in a docker container to sloppy.io",
                                Instant.parse("2022-05-02T13:50:00Z"),
                                "/assets/images/ben-kolde-bs2Ba7t69mM-unsplash.jpg",
                                Set.of("plantuml", "java", "tools", "spring boot", "docker"),
                                "I'm a big fan of including [PlantUML diagrams](http://plantuml.com/) for documentation using [Asciidoc](http://asciidoctor.org/) and [Spring REST docs](https://projects.spring.io/spring-restdocs/). Using PlantUML also saves time when visualising software designs as the cycle time of editing and seeing the new diagram can be much shorter that using drag and drop tools like Visio. and is able to generate a UML diagram.",
                                "Deploying Spring Boot applications");

                readingListItemEditor.create("Confessions of a low code convert", Instant.parse("2022-05-03T13:50:00Z"),
                                "https://thenewstack.io/confessions-of-a-low-code-convert/", "Low code for the win",
                                Set.of("low code"));

        }

}
