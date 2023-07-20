

-------------

#### Spring Framework:

<br>


```java
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;

    // annotations:
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;


    @Service
    @Transactional
    @RequiredArgsConstructor
    public class BookmarkService {

       private final BookmarkRepository repository;


       @Transactional(readOnly = true)
       public BookmarksDTO getBookmarks(Integer page) {

          int pageNo = Math.max(page - 1, 0) ;
          Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
          return new BookmarksDTO(repository.findAll(pageable));
     }

 }
 ```


  <em>@Transactional</em>: 


  <em>@Service</em>:


------------------------


```java
   @Component
   @RequiredArgsConstructor // constructor injection
   public class DataInitializer implements CommandLineRunner {

     private final BookmarkRepository repository;

     @Override
     public void run(String... args) throws Exception {

         repository.save(new Bookmark(null, "SivaLabs", "https://sivalabs.in", Instant.now()));
         repository.save(new Bookmark(null, "SpringBlog", "https://spring.io/blog", Instant.now()));
     }
    
  }
```

   <em>@Component</em>: 


<br>

```java
import com.dev.bookmarker.domain.BookmarkService;
import com.dev.bookmarker.domain.BookmarksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return bookmarkService.getBookmarks(page);
    }  
}
```

<em>@RequestParam(name = "queryParam", defaultValue="defaultValue")</em>

<br>
<br>


---------------------------------
<br>

#### Javax Persistance:
<br>


 ```java


    import javax.persistence.Id;
    import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.SequenceGenerator;
    import javax.persistence.Table;

    
    @Entity
    @Table(name = "bookmarks")
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Bookmark {

        @Id
        @SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bm_id_seq_gen")
        private Long id;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private String url ;

        private Instant createdAt;

    }
  ```  
    
<br>

[Database Sequence](https://stackoverflow.com/a/1649126)
 
<br>


----------------------------

#### Jackson Json

<br>


```java

    import com.fasterxml.jackson.annotation.JsonProperty;

    public class BookMarksDTO {
    
        @JsonProperty("isFirst")
        boolean isFirst;

    }
```

<br>

<em>@JsonProperty</em>:

--------------------------------------