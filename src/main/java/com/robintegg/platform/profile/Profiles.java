package com.robintegg.platform.profile;

import org.springframework.stereotype.Service;

@Service
public class Profiles {
    
    private Profile profile = new Profile();

    public Profile getProfile() {
        return profile;
    }

}
