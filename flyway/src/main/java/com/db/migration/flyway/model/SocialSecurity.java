package com.db.migration.flyway.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name= "SOCIAL_SECURITY")
public class SocialSecurity {
    @Id
    @GeneratedValue
    private int id;
    private String ssid;
    private String mobile_number;
}
