package com.dev.bookmarker.domain;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@Transactional // note: the annotation is applied on a class
@RequiredArgsConstructor // instead of manually creating a constructor to initialize BookmarkRepository property
public class BookmarkService {

    private final BookmarkRepository repository;
    private final BookmarkMapper bookmarkMapper;


    @Transactional(readOnly = true)  //  we use annotation here to tell hibernate to not do dirty checking
                                     //  in which managed entities are compared with loaded entites and
                                     //  in case of any changes they are persisted during flush time
                                     //
                                     //
                                     // tell hibernate that there is no data modifications happening
                                    // so that it does not do dirty checking which saves some performance
    public BookmarksDTO getBookmarks(Integer page) {

        int pageNo = Math.max(page - 1, 0) ;


        // Paging configuration:   pageNo you wish to view,
        //                         10 elements per page,
        //                         desc sort on createdAt property
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");


        // bookmarkPage of type Page<BookMarkDTO> has additional metadata
        // such as number of pages, current page, totalNumberOfElements
        // isFirstPage, isLastPage which is used in BookmarksDTO
        Page<BookmarkDTO> bookmarkPage = repository.getBookmarks(pageable);

        return new BookmarksDTO(bookmarkPage);
    }

    @Transactional(readOnly = true)
    public BookmarksDTO searchBookmarks(String query, Integer page) {

        int pageNo = Math.max(page - 1, 0) ;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");


        Page<BookmarkDTO> bookmarkPage = repository.searchBookmarks(query, pageable);

       // class based projection and
       // spring-data-jpa repository subject, predicate and modifier methods
       // Page<BookmarkDTO> bookmarkPage = repository.findByTitleContainsIgnoreCase(query, pageable);

      // interface based projection and
      // spring-data-jpa repository methods
      // Page<BookmarkVM> bookmarkvmPage = repository.findByTitleContainsIgnoreCase(query, pageable);

        return new BookmarksDTO(bookmarkPage);

    }

     // the @Transaction (which set for the class) is not used as this is
     // not read operation and we do not want hibernate to stop dirty checking
    public BookmarkDTO createBookmark(CreateBookmarkRequest request) {

        Bookmark bookmark = new Bookmark(null, request.getTitle(), request.getUrl(), Instant.now()) ;
        return bookmarkMapper.toDTO(repository.save(bookmark));
    }
}