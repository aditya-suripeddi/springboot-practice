package com.dev.bookmarker.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 *   BookmarkDTO is the data transfer object corresponding
 *   to Bookmark
 */
@Getter
@Setter
//
//                  a.   when we created the following method in BookmarksRepository:
//
//                           @Query("SELECT new com.dev.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b")
//                           Page<BookmarkDTO> getBookmarks(Pageable pageable);
//
//                       we mentioned in @Query which constructor to use (@AllArgsConstructor):
//                       com.dev.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt)
//
//
//                  b.  when we created the following method in BookmarksRepository:
//
//                      Page<BookmarkDTO> findByTitleContainsIgnoreCase(String query, Pageable pageable);
//
//                      spring data jpa does not know which constructor to use to instantiate BookmarkDTO
//                      - the noargs or allargs, so we comment out noargs constructor lombok annotation below
//

//@NoArgsConstructor

@AllArgsConstructor
public class BookmarkDTO {  // class based dto projection

    private Long id;
    private String title;
    private String url;
    private Instant createdAt;

}