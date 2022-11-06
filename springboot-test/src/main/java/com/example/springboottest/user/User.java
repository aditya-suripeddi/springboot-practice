package com.example.springboottest.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



// @Document(collection = "cname"):
//                create `cname` collection if it does not exist inside
//                db provided in application.properties
//



@Document(collection = "users") // create `users` collections with
                                // db mentioned in application.properties
                                // for this document
public class User {

    @JsonIgnore
    @Id
    private String id;
    private String name;
    private String address;
    private String ssid;
    private int age;

    public User(String name, String address, String ssid, int age) {
        this.name = name;
        this.address = address;
        this.ssid = ssid;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public String getSsid() {
        return ssid;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", address=" + address + ", ssid=" + ssid + ", age=" + age + "]";
    }

   public boolean isEqual(User u) {
        return  ( u != null )  && 
                ( ssid.compareTo(u.getSsid()) == 0 ) && 
                ( this.getName().compareTo(u.getName()) == 0 ) && 
                ( this.getAddress().compareTo(u.getAddress()) == 0 ) &&
                ( this.getAge() == u.getAge() );
    }

}


