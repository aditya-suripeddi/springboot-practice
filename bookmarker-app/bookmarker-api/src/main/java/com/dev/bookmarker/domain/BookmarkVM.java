package com.dev.bookmarker.domain;

import java.time.Instant;

// illustrating interface based dto projection
public interface BookmarkVM {

    Long getId(); // java bean naming convention should be followed
    String getTitle();
    String getUrl();
    Instant getCreatedAt();
}
