package com.robintegg.platform.home;

import com.robintegg.platform.profile.Profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LatestContent latestContent;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return new Profile();
    }

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String getHome(Model model) {
        model.addAttribute("latest", latestContent.getAll());
        return "home";
    }

}
