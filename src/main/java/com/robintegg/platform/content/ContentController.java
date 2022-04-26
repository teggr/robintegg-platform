package com.robintegg.platform.content;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContentController {

    @RequestMapping(path = "/**",method = RequestMethod.GET)
    public String getContent(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("path", httpRequest.getPathInfo());
        return "content";
    }

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }

}
