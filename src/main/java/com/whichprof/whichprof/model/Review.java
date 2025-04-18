package com.whichprof.whichprof.model;

public class Review {
    private String courseName;
    private int rating;
    private boolean takeAgain;
    private String difficulty;
    private String review;

    // Getter for courseName
    public String getCourseName() {
        return courseName;
    }

    // Setter for courseName
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Getter for rating
    public int getRating() {
        return rating;
    }

    // Setter for rating
    public void setRating(int rating) {
        this.rating = rating;
    }

    // Getter for takeAgain
    public boolean isTakeAgain() {
        return takeAgain;
    }

    // Setter for takeAgain
    public void setTakeAgain(boolean takeAgain) {
        this.takeAgain = takeAgain;
    }

    // Getter for difficulty
    public String getDifficulty() {
        return difficulty;
    }

    // Setter for difficulty
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // Getter for review
    public String getReview() {
        return review;
    }

    // Setter for review
    public void setReview(String review) {
        this.review = review;
    }
}
