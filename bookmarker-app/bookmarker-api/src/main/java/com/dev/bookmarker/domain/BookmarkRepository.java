package com.dev.bookmarker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//
//      Note:
//
//                     We have two database drivers in our pom.xml, H2 and postgresql
//
//
//                     So which datasource gets picked by spring data jpa when developer
//                     does not explicitly define configure datasource in application.properties
//
//                                 spring.datasource.url=
//                                 spring.datasource.username=
//                                 spring.datasource.password=
//                                 spring.datasource.driverClassName=
//                                 spring.datasource.database-platform=
//
//                      or via through a java @Configuration class ?
//
//
//
//                      https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa#2-configuration
//
//                      states that "Spring Boot configures [Hibernate] as the [default [JPA provider]],
//                                   Boot automatically configures the DataSource if the corresponding
//                                   database dependency is present on the classpath."
//
//                                   "If we want to use JPA with MySQL database, we need the mysql-connector-java dependency.
//                                    We'll also need to define the DataSource configuration."



//
//   To access the h2 console:
//
//        1. Go to http://localhost:8080/h2-console
//
//        2. Search for the line below in startup logs and copy the h2 JDBC URL
//
//            2023-06-29 11:45:30.902  INFO 14629 --- [  restartedMain] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:67a68dc7-f42b-4c5c-92be-8aeb3b92e205'
//
//
//        3.  The password by default is empty ("") so sign-in
//

//  public interface DomainModelPojoRepository extends JpaRepository<DomainModelPojo,
//                                                                   Data Type of Primary Key>
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    //
    //  (class based) spring data projections:
    //
    //                             More often than not, we don't need all the properties of the returned objects.
    //
    //                             In such cases, we might want to retrieve data as objects of customized types.
    //                             These types reflect partial views of the root class, containing only the properties we care about.
    //                             This is where projections come in handy.
    //
    //
    //                             https://www.baeldung.com/spring-data-jpa-projections
    //
    //
    @Query("SELECT new com.dev.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b")
    Page<BookmarkDTO> getBookmarks(Pageable pageable);



    // Note: we use NAMED PARAMETER ':query' in the search query below:
    @Query("""
               SELECT
                      new com.dev.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt)
               FROM
                      Bookmark b
               WHERE
                      lower(b.title) LIKE lower(concat('%', :query, '%'))
           """)
    // to refer named parameters we use @Param:
    // https://stackoverflow.com/a/66398721
    Page<BookmarkDTO> searchBookmarks(@Param("query") String query, Pageable pageable);

    //
    // intellij ultimate edition (ide) shows suggestions
    // of various subject keyword methods (with predicates and modifiers)
    // supported by spring data jpa respositories
    //
    // Browse the following tables:
    //
    //         Query Subject Keywords Table
    //         Query Predicate Keywords Table
    //         Query Prdicate Modifier Keywords Table
    //
    // at  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
    //
    //Page<BookmarkDTO> findByTitleContainsIgnoreCase(String query, Pageable pageable);

    Page<BookmarkVM> findByTitleContainsIgnoreCase(String query, Pageable pageable);
}