package com.robintegg.platform.posts;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Posts {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getByUri(String requestURI) {
        return postRepository.findByUri(requestURI);
    }

}
