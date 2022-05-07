package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Posts {

    private final PostPathResolver pathResolver;

    private Map<String, Post> uriToPost = new HashMap<>();

    @PostConstruct
    public void init() {
        Instant date = Instant.parse("2022-05-03T13:50:00Z");
        String title = "First look at Java support in Visual Studio Code";
        String uri = pathResolver.path(date, title);
        Post post = new Post(
                uri,
                title,
                date,
                "/assets/images/ben-kolde-bs2Ba7t69mM-unsplash.jpg",
                Set.of("visual studio", "java", "tools", "ide", "visual studio plugins"),
                "Microsoft recently released a [Visual Studio Java extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) and a [Java Test Runner](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test) to the Visual Studio Code market place. The [Visual Studio Java extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) adds debugging support to the [Red Hat language support for Java](https://marketplace.visualstudio.com/items?itemName=redhat.java). The [Java Test Runner](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test) adds support for executing JUnit tests.",
                "Java in Visual Studio");
        uriToPost.put(post.getUri(), post);

         date = Instant.parse("2022-05-02T13:50:00Z");
         title = "Deploying a PlantUML Spring Boot application in a docker container to sloppy.io";
         uri = pathResolver.path(date, title);
         post = new Post(
                uri,
                title,
                date,
                "/assets/images/ben-kolde-bs2Ba7t69mM-unsplash.jpg",
                Set.of("plantuml","java","tools","spring boot","docker"),
                "I'm a big fan of including [PlantUML diagrams](http://plantuml.com/) for documentation using [Asciidoc](http://asciidoctor.org/) and [Spring REST docs](https://projects.spring.io/spring-restdocs/). Using PlantUML also saves time when visualising software designs as the cycle time of editing and seeing the new diagram can be much shorter that using drag and drop tools like Visio. and is able to generate a UML diagram.",
                "Deploying Spring Boot applications");
        uriToPost.put(post.getUri(), post);
    }

    public List<Post> getPosts() {
        return uriToPost.values().stream()
                .sorted(Comparator.comparing(Post::getDate))
                .toList();
    }

    public Post getByUri(String requestURI) {
        return uriToPost.get(requestURI);
    }

}
