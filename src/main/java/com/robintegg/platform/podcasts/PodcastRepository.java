package com.robintegg.platform.podcasts;

import org.springframework.data.jpa.repository.JpaRepository;

interface PodcastRepository extends JpaRepository<Podcast,Long> {

    Podcast findByUri(String requestURI);

}
