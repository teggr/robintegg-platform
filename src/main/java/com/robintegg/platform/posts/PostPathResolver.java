package com.robintegg.platform.posts;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

@Component
public class PostPathResolver {

    public String path( Instant date, String title ) {

        LocalDateTime localDateTime = date.atOffset(ZoneOffset.UTC).toLocalDateTime();

        String path = "/" + localDateTime.format(DateTimeFormatter.ofPattern( "yyyy/MM/dd") );

        path = path + "/" + title + ".html";

        return path.toLowerCase().replaceAll(" ", "-");

    }

}
