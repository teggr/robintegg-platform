package com.robintegg.platform.posts;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

@Component
public class PostPathResolver {

    public String path(Post post) {

        LocalDateTime localDateTime = post.getDate().atOffset(ZoneOffset.UTC).toLocalDateTime();

        String path = localDateTime.format(DateTimeFormatter.ofPattern( "yyyy/MM/dd") );

        path = path + "/" + post.getTitle() + ".html";

        return path.toLowerCase().replaceAll(" ", "-");

    }

}
