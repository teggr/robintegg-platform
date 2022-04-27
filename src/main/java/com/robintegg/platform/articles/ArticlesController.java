package com.robintegg.platform.articles;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ArticlesController {

    @RequestMapping(path = "/**",method = RequestMethod.GET)
    public String getArticles(HttpServletRequest httpRequest, Model model) {
        // content by request uri
        // /2020/03/08/testing-spring-boot-applications-with-testcontainers-revisited-2020.html >> generate content based on 
        // do we need to see if a url can be resolved here 
        System.out.println(httpRequest.getRequestURI());
        model.addAttribute("path", httpRequest.getPathInfo());
        return "content";
    }

}
