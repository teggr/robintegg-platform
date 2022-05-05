package com.robintegg.platform.podcasts;

import javax.servlet.http.HttpServletRequest;

import com.robintegg.platform.profile.Profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/podcasts")
@RequiredArgsConstructor
public class PodcastsController {

    private final Podcasts podcasts;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return new Profile();
    }
    
    @GetMapping
    public String getReadingList(Model model){
        model.addAttribute("podcasts", podcasts.getItems());
        return "podcasts";
    }

    @GetMapping("/**")
    public String getPodcast(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("podcast", podcasts.getPodcast(httpRequest.getRequestURI()));
        return "podcast";
    }

}
