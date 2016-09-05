package com.jsy.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table USER.
 */
public class User {

    private Long id;
    /** Not-null value. */
    private String name;
    private java.util.Date gender;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, java.util.Date gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

    public java.util.Date getGender() {
        return gender;
    }

    public void setGender(java.util.Date gender) {
        this.gender = gender;
    }

}
