package com.dashengz.firebasetest;

/**
 * Created by Jonathan on 3/21/16.
 * User class for Firebase Test
 */
public class User {
    private String name;
    private int gender; // 0 Male, 1 Female, 2 Secret
    private int age;
    private String description;

    public User() {
    }

    public User(String name, int gender, int age, String description) {
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

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }
}