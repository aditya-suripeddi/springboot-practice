package com.dev.bookmarker.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

/**
 *  BookmarksDTO is data transfer object that
 *  encapsulates following information:
 *     a. bookmarks on a given page
 *     b. pagination related metadata: total bookmarks, current page number
 */
@Getter
@Setter
public class BookmarksDTO {

      private List<BookmarkDTO> data;
      private long totalElements;
      private int totalPages;
      private int currentPage;

      @JsonProperty("isFirst")
      private boolean isFirst;

      @JsonProperty("isLast")
      private boolean isLast;

      private boolean hasNext;
      private boolean hasPrevious;

      public BookmarksDTO(Page<BookmarkDTO> bookmarkPage) {
            this.setData(bookmarkPage.getContent());
            this.setTotalElements(bookmarkPage.getTotalElements());
            this.setTotalPages(bookmarkPage.getTotalPages());
            this.setCurrentPage(bookmarkPage.getNumber() + 1);
            this.setFirst(bookmarkPage.isFirst());
            this.setLast(bookmarkPage.isLast());
            this.setHasNext(bookmarkPage.hasNext());
            this.setHasPrevious(bookmarkPage.hasPrevious());
      }
}