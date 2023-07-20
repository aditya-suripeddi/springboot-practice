package com.dev.bookmarker.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GenerationType;

import java.time.Instant;

/**
 *  Bookmark is the domain model that we use for reading
 *  from and writing to database
 */
@Entity
@Table(name = "bookmarks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {


   //
   //
   //  sequence is a database feature / object like a table
   //  for generating unique values (also see reference)
   //
   //  when spring.jpa.hibernate.ddl-auto is set to update in application.properties
   //  you will notice the following query run by hibernate that creates a sequence
   //  in database (comment out flyway in pom.xml & application.properties, run the application and from logs search "HibernateDialect" ):
   //
   //  2023-06-29 12:55:59.477  INFO 19995 --- [  restartedMain] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
   //  2023-06-29 12:55:59.605  INFO 19995 --- [  restartedMain] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
   //  Hibernate: create table bookmarks (id bigint not null, created_at timestamp, title varchar(255) not null, url varchar(255) not null, primary key (id))
   //  Hibernate: create sequence bm_id_seq start with 1 increment by 50
   //
   //
   //  also refer: https://stackoverflow.com/questions/1649102/what-is-a-sequence-database-when-would-we-need-it/1649126#1649126
   //              https://stackoverflow.com/questions/1649102/what-is-a-sequence-database-when-would-we-need-it/1649126#1649126
   //
   //
   //              V1__create_bookmarks_table.sql:
   //
   //                                CREATE sequence bm_id_seq start WITH 1 INCREMENT BY 50 ;
   //
   //                                CREATE TABLE bookmarks (
   //                                          id BIGINT DEFAULT NEXTVAL('bm_id_seq') NOT NULL,
   //                                          title VARCHAR(255) NOT NULL,
   //                                          url VARCHAR(255) NOT NULL,
   //                                          created_at TIMESTAMP,
   //                                          primary key (id)
   //                                 ) ;
   //
   //


   //sequenceName is the sequence defined in database
   @Id
   @SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bm_id_seq_gen")
   private Long id;

   @Column(nullable = false)
   private String title;

   @Column(nullable = false)
   private String url ;

   private Instant createdAt;  // note the data type used Instant

}