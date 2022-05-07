package com.robintegg.platform.home;

import java.time.Instant;
import java.util.Set;

public interface ContentSummary {

    String getTitle();

    String getSubtitle();

    Set<String> getTags();

    String getLink();

    Instant getDate();

}
