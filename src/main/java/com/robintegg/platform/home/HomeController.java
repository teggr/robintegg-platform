package com.robintegg.platform.home;

import com.robintegg.platform.index.IndexedContents;
import com.robintegg.platform.profile.Profile;
import com.robintegg.platform.profile.Profiles;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final IndexedContents indexedContents;
    private final Profiles profiles;

    @ModelAttribute("profile")
    public Profile getProfile() {
        return profiles.getProfile();
    }

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String getHome(Model model, Pageable pageable) {
        model.addAttribute("latest", indexedContents.getLatest(pageable));
        return "home";
    }

}
