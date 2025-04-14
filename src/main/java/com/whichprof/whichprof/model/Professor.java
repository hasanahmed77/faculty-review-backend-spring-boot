package com.whichprof.whichprof.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("professor")
public class Professor {

    @Id
    private String id;

    private String name;
    private String dept;
    private String initial;
    private int rating;
    private boolean takeAgain;
    private String difficulty;
    private String courseName;
    private List<String> reviews;
    private String university;

    // constructors
    public Professor() {
    }

    public Professor(String name, String dept, String initial, int rating, boolean takeAgain, String difficulty,
            String courseName, List<String> reviews, String university) {
        this.name = name;
        this.dept = dept;
        this.initial = initial;
        this.rating = rating;
        this.takeAgain = takeAgain;
        this.difficulty = difficulty;
        this.courseName = courseName;
        this.reviews = reviews;
        this.university = university;
    }

    // getters & setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isTakeAgain() {
        return takeAgain;
    }

    public void setTakeAgain(boolean takeAgain) {
        this.takeAgain = takeAgain;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
