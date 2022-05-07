package com.robintegg.platform;

import java.time.Instant;
import java.util.Set;

import com.robintegg.platform.bookshelf.BookEditor;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestData implements ApplicationRunner {

    private final BookEditor bookEditor;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Instant date = Instant.parse("2022-05-03T13:50:00Z");
        String title = "Developer Hegemony";
       
    
        bookEditor.create( title, date, "Viva la developer revolution",Set.of("career") );

    }
    
}
