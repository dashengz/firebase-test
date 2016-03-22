package com.dashengz.firebasetest;

/**
 * Created by Jonathan on 3/21/16.
 * User class for Firebase Test
 */
public class User {
    private String name;
    private int gender;
    private String age;
    private String description;

    public User() {
    }

    public User(String name, int gender, String age, String description) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }
}