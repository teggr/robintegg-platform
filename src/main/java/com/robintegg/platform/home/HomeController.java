package com.robintegg.platform.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LatestContent latestContent;

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String getHome(Model model) {
        model.addAttribute("latest", latestContent.getAll());
        return "home";
    }

}
