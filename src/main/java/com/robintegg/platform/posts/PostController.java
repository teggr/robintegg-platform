package com.robintegg.platform.posts;

import javax.servlet.http.HttpServletRequest;

import com.robintegg.platform.profile.Profile;
import com.robintegg.platform.profile.Profiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final Posts posts;
    private final Profiles profiles;

    private PostContentRenderer contentRenderer = new PostContentRenderer();

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @RequestMapping(path = "/{year}/{month}/{dayOfMonth}/*.html", method = RequestMethod.GET)
    public String getPost(HttpServletRequest httpRequest, Model model) {
        System.out.println(httpRequest.getRequestURI());
        model.addAttribute("post", posts.getByUri(httpRequest.getRequestURI()));
        model.addAttribute("renderer", contentRenderer);
        return "post";
    }

}
