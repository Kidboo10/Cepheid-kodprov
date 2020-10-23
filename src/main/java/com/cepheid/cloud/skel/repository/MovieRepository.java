package com.cepheid.cloud.skel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cepheid.cloud.skel.model.Movie;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByYear(String year);
    List<Movie> findMovieByDirector(String director);
    List<Movie> findMovieByRating(String rating);
}
