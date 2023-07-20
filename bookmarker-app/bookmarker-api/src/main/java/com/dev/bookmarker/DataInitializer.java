package com.dev.bookmarker;

import com.dev.bookmarker.domain.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


// once application starts, the run method of class
// that implements CommandLineRunner is called
//
// also refer: https://www.amitph.com/spring-boot-runners/

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {



    // create initializer with repository as dependency using
    // lombok's @RequiredArgsConstructor (constructor injection)
    // and use the repository to initialize data

    private final BookmarkRepository repository;

    @Override
    public void run(String... args) throws Exception {

       /*
            repository.save( new Bookmark(null, "SpringBlog", "https://spring.io/blog", Instant.now()) );
            repository.save( new Bookmark(null, "Quarkus", "https://quarkus.io", Instant.now()) );
            repository.save( new Bookmark(null, "SivaLabs", "https://sivalabs.in", Instant.now()) );
            repository.save( new Bookmark(null, "Micronaut", "https://micronaut.io", Instant.now()) );
            repository.save( new Bookmark(null, "JOOQ", "https://jooq.org", Instant.now()) );
            repository.save( new Bookmark(null, "VladMihalcea", "https://vladmihalcea.com", Instant.now()) );
            repository.save( new Bookmark(null, "Thoughts on Java", "https://thorben-janssen.com", Instant.now()) );
            repository.save( new Bookmark(null, "DZone", "https://dzone.com", Instant.now()) );
            repository.save( new Bookmark(null, "DevOpsBookmarks", "https://devopsbookmarks.com/", Instant.now()) );
            repository.save( new Bookmark(null, "k8s docs", "https://kubernetes.io/docs/home", Instant.now()) );
            repository.save( new Bookmark(null, "Expressjs", "https://expressjs.com", Instant.now()) );
            repository.save( new Bookmark(null, "Marco Behler", "https://www.marchobehler.com", Instant.now()) );
            repository.save( new Bookmark(null, "baeldung", "https://www.baeldung.com", Instant.now()) );
            repository.save( new Bookmark(null, "devglan", "https://www.devglan.com", Instant.now()) );
            repository.save( new Bookmark(null, "linuxize", "https://linuxize.com", Instant.now()) );
        */
    }
}
