package com.db.migration.flyway.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name= "USERS")
public class User {

   @Id
   @GeneratedValue
   private int id;
   private String username;
   private String first_name;
   private String last_name;
   private String email;
   private String ssid;
   private String mobile; // added as part of updated requirements with V2.1_addColumn.sql
}
