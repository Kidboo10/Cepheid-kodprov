package com.cepheid.cloud.skel.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie extends AbstractEntity {

    private String title;
    private String rating;
    private String genre;
    private String director;
    private String year;

    @OneToMany(mappedBy = "movie", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Description> descriptions = new ArrayList<Description>();

    public Movie() {
    }

    public Movie(String title, String rating, String genre, String director, String year) {
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.director = director;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + getTitle() + '\'' +
                ", rating='" + getRating() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", director='" + getDirector() + '\'' +
                ", year='" + getYear() + '\'' +
                ", descriptions=" + getDescriptions() +
                ", movieId=" + getId() +
                '}';
    }
}
