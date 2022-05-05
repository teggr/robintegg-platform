package com.robintegg.platform.home;

import java.time.Instant;
import java.util.List;

public interface ContentSummary {

    String getTitle();

    String getSubtitle();

    List<String> getTags();

    String getLink();

    Instant getDate();

}
