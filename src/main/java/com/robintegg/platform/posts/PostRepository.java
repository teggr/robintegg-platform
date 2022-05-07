package com.robintegg.platform.posts;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<Post,Long> {

    Post findByUri(String uri);

}
