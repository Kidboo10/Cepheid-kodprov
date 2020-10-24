package com.cepheid.cloud.skel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Description extends AbstractEntity {

    private String title;
    private String review;
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Movie movie;

    public Description() {
    }

    public Description(String title, String review, String date, Movie movie) {
        this.title = title;
        this.review = review;
        this.date = date;
        this.movie = movie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String description) {
        this.review = description;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Description{" +
                "title='" + getTitle() + '\'' +
                ", review='" + getReview() + '\'' +
                ", date=" + getDate() +
                ", mId=" + getId() +
                ", movieId=" + getMovie().getId() +
                '}';
    }
}
