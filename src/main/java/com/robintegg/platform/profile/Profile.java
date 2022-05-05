package com.robintegg.platform.profile;

import java.util.List;

import lombok.Getter;

@Getter
public class Profile {

    private String name = "Robin Tegg";
    private String email = "robin@tegg.me.uk";
    private String tagLine = "A Java developer. Based in Leeds, England. Has interest in Software Architecture, Testing, Automation, Tooling and UI design.";
    private List<SocialMediaAccount> socialMediaAccounts = List.of(
        new SocialMediaAccount("teggr", "https://github.com/teggr","fa-github"),
        new SocialMediaAccount("robintegg", "https://www.linkedin.com/in/robintegg","fa-facebook"),
        new SocialMediaAccount("robintegg", "https://www.twitter.com/robintegg","fa-twitter")
    );


}
