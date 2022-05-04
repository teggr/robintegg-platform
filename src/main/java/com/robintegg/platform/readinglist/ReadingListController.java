package com.robintegg.platform.readinglist;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reading-list")
@RequiredArgsConstructor
public class ReadingListController {

    private final ReadingList readingList;
    
    @GetMapping
    public String getReadingList(Model model){
        model.addAttribute("items", readingList.getItems());
        return "reading-list";
    }

    @GetMapping("/**")
    public String getReadingListIyem(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("item", readingList.getItem(httpRequest.getRequestURI()));
        return "reading-list-item";
    }

}
