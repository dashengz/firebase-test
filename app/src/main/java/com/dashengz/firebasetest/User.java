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
    private boolean partTime;
    private boolean fullTime;
    private boolean internship;

    public User() {
    }

    public User(String name, int gender, int age, String description, boolean partTime, boolean fullTime, boolean internship) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.partTime = partTime;
        this.fullTime = fullTime;
        this.internship = internship;
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

    public boolean isPartTime() {
        return partTime;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public boolean isInternship() {
        return internship;
    }
}