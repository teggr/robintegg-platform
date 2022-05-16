package com.robintegg.platform.readinglist;

import javax.servlet.http.HttpServletRequest;

import com.robintegg.platform.profile.Profile;
import com.robintegg.platform.profile.Profiles;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reading-list")
@RequiredArgsConstructor
public class ReadingListController {

    private final ReadingList readingList;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }
    
    @GetMapping
    public String getReadingList(Model model, Pageable pageable){
        model.addAttribute("items", readingList.getItems(pageable));
        return "reading-list";
    }

    @GetMapping("/**")
    public String getReadingListIyem(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("item", readingList.getItem(httpRequest.getRequestURI()));
        return "reading-list-item";
    }

}
