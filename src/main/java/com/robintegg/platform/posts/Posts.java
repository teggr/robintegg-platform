package com.robintegg.platform.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Posts {

    private final PostRepository postRepository;

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post getByUri(String uri) {
        return postRepository.findByUri(uri);
    }

    public Post getById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

}
