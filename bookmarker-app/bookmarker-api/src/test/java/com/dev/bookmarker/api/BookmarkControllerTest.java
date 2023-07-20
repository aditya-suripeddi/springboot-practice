package com.dev.bookmarker.api;

import com.dev.bookmarker.domain.Bookmark;
import com.dev.bookmarker.domain.BookmarkRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// SpringBootTest.WebEnironment.RANDOM_PORT:
//
//      the build server as part of CI/CD pipeline could be
//      running tests for multiple applications parallely.
//
//      better to choose a random port to run the test
//      to avoid conflicts with other ports on build server


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
       "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo" // demo -> database name
})
//@Disabled  // to disable this test
class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    private List<Bookmark> bookmarks;

    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAllInBatch();
        bookmarks = new ArrayList<>();

        bookmarks.add( new Bookmark(null, "SpringBlog", "https://spring.io/blog", Instant.now()) );
        bookmarks.add( new Bookmark(null, "Quarkus", "https://quarkus.io", Instant.now()) );
        bookmarks.add( new Bookmark(null, "SivaLabs", "https://sivalabs.in", Instant.now()) );
        bookmarks.add( new Bookmark(null, "Micronaut", "https://micronaut.io", Instant.now()) );
        bookmarks.add( new Bookmark(null, "JOOQ", "https://jooq.org", Instant.now()) );
        bookmarks.add( new Bookmark(null, "VladMihalcea", "https://vladmihalcea.com", Instant.now()) );
        bookmarks.add( new Bookmark(null, "Thoughts on Java", "https://thorben-janssen.com", Instant.now()) );
        bookmarks.add( new Bookmark(null, "DZone", "https://dzone.com", Instant.now()) );
        bookmarks.add( new Bookmark(null, "DevOpsBookmarks", "https://devopsbookmarks.com/", Instant.now()) );
        bookmarks.add( new Bookmark(null, "k8s docs", "https://kubernetes.io/docs/home", Instant.now()) );
        bookmarks.add( new Bookmark(null, "Expressjs", "https://expressjs.com", Instant.now()) );
        bookmarks.add( new Bookmark(null, "Marco Behler", "https://www.marchobehler.com", Instant.now()) );
        bookmarks.add( new Bookmark(null, "baeldung", "https://www.baeldung.com", Instant.now()) );
        bookmarks.add( new Bookmark(null, "devglan", "https://www.devglan.com", Instant.now()) );
        bookmarks.add( new Bookmark(null, "linuxize", "https://linuxize.com", Instant.now()) );

        // save the bookmarks collection to database
        bookmarkRepository.saveAll(bookmarks);

    }


    @Test
        // make sure you select junit.jupiter @Test annotation,
        // if you have junit4 and junit5 dependencies in project
        // you may accidentally import junit4's @Test annotation
    void shouldGetBookMarks_v1() throws Exception {

        // note:  when "?page=1" is not passed as query param in url
        //        controller takes default page=1

        mockMvc.perform(get("/api/bookmarks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(2)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(1)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(true)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(false)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(true)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(false)));

    }


    @ParameterizedTest
    @CsvSource({
            "1, 15, 2, 1, true, false, true, false",
            "2, 15, 2, 2, false, true, false, true"
    })
    void shouldGetBookMarks_v2(int pageNo,
                            int totalElements,
                            int totalPages,
                            int currentPage,
                            boolean isFirst,
                            boolean isLast,
                            boolean hasNext,
                            boolean hasPrevious) throws Exception {


        mockMvc.perform(get("/api/bookmarks?page=" + pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)));

    }


    @Test
    void shouldFailToCreateBookmarkWhenUrlIsNotPresent() throws Exception {

        mockMvc.perform(
                post("/api/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title" : "Sample bookmark Title"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field",  is("url")))
                .andExpect(jsonPath("$.violations[0].message",  is("Url should not be empty")))
                .andReturn() ;
                // why andReturn() is used on mockMvc ?
               // https://stackoverflow.com/questions/18336277/how-to-check-string-in-response-body-with-mockmvc



    }

}