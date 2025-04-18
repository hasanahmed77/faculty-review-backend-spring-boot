package com.whichprof.whichprof.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("professor")
public class Professor {

    @Id
    private String id;

    private String name;
    private String dept;
    private String initial;
    private String courseName;
    private List<Review> reviews;
    private String university;

    @CreatedDate
    private Date createdAt;

    // constructors
    public Professor() {
    }

    public Professor(String name, String dept, String initial,
            String courseName, List<Review> reviews, String university) {

        this.name = name;
        this.dept = dept;
        this.initial = initial;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
