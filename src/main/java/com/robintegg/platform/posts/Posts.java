package com.robintegg.platform.posts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class Posts {

    private List<Post> posts = new ArrayList<>();

    @PostConstruct
    public void init() {
        posts.add(new Post("First look at Java support in Visual Studio Code"));
    }

    public List<Post> getPosts() {
        return posts;
    }

}
