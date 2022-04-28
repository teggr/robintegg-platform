package com.robintegg.platform.posts;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class Posts {

    private List<Post> posts = new ArrayList<>();

    @PostConstruct
    public void init() {
        posts.add(new Post(
            "First look at Java support in Visual Studio Code",
            Instant.now(),
            "/assets/images/ben-kolde-bs2Ba7t69mM-unsplash.jpg",
            List.of("visual studio","java","tools","ide","visual studio plugins"),
            "Microsoft recently released a [Visual Studio Java extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) and a [Java Test Runner](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test) to the Visual Studio Code market place. The [Visual Studio Java extension pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) adds debugging support to the [Red Hat language support for Java](https://marketplace.visualstudio.com/items?itemName=redhat.java). The [Java Test Runner](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test) adds support for executing JUnit tests."
            ));
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post getByUri(String requestURI) {
        return posts.get(0);
    }

}
