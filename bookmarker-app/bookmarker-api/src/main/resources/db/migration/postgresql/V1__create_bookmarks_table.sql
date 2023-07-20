
 CREATE sequence bm_id_seq start WITH 1 INCREMENT BY 50 ;

 CREATE TABLE bookmarks (
        id BIGINT DEFAULT NEXTVAL('bm_id_seq') NOT NULL,
        title VARCHAR(255) NOT NULL,
        url VARCHAR(255) NOT NULL,
        created_at TIMESTAMP,
        primary key (id)
  ) ;

