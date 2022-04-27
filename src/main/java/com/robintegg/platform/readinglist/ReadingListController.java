package com.robintegg.platform.readinglist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReadingListController {

    private final ReadingList readingList;
    
    @GetMapping("/reading-list")
    public String getReadingList(Model model){
        model.addAttribute("items", readingList.getItems());
        return "reading-list";
    }

}
