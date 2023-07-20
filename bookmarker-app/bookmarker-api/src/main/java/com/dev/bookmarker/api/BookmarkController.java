package com.dev.bookmarker.api;


import com.dev.bookmarker.domain.BookmarkDTO;
import com.dev.bookmarker.domain.BookmarkService;
import com.dev.bookmarker.domain.BookmarksDTO;
import com.dev.bookmarker.domain.CreateBookmarkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    /**
     *
     * @param  page : Page number requested
     *
     * @return Bookmarks on the requested page with metadata
     */
    @GetMapping                    // "@RequestParam(name = "page", defaultValue = "1") Integer page" :
                                     //
                                     //       1. if defaultValue is provided, then the parameter is considered
                                     //         optional or required = false
                                     //
                                     //       2. we 'BIND' the request parameter 'page' to variable page
                                     //

    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "query", defaultValue = "") String query) {

        if( query.isEmpty() ) { // use curly braces even if you have one line
            return bookmarkService.getBookmarks(page);
        }

        return bookmarkService.searchBookmarks(query, page);
    } // press Alt + Insert + (Fn key maybe required ) to create Test class for this method


    // Note:
    //
    //    we use problem-spring-web-starter library by zalando to make sure that request body
    //    to handle validation errors in a request and send a proper http response body
    //    highlighting the errors found in request payload
    //

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkDTO createBookmarks(@RequestBody @Valid CreateBookmarkRequest request) {
           return bookmarkService.createBookmark(request);
    }

}