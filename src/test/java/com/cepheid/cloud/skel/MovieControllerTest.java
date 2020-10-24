package com.cepheid.cloud.skel;

import java.util.Collection;

import com.cepheid.cloud.skel.controller.MovieController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.cepheid.cloud.skel.model.Movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MovieControllerTest extends TestBase {

    @Autowired
    private MovieController movieController;

    @Test
    public void testGetAllMovies() {
        Collection<Movie> movies = movieController.getAllMovies();
        assertEquals(movieController.getAllMovies().size(), movies.size());
    }

    @Test
    public void testGetYear3030ShouldReturnHttpStatusNotFound() {
        assertThat(movieController.getMovieByYear("3030").getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetYear2019ShouldReturnHttpStatusOK() {
        assertThat(movieController.getMovieByYear("2019").getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testGetDirectorJordanPeeleShouldReturnHttpStatusOK() {
        assertThat(movieController.getMovieByDirector("Jordan Peele").getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testGetUnknownDirectorShouldReturnHttpStatusNotFound() {
        assertThat(movieController.getMovieByDirector("Unknown").getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_FOUND);
        ;
    }

    @Test
    public void testGetRatingPG13ShouldReturnHttpStatusOK() {
        assertThat(movieController.getMovieByRating("pg-13").getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testGetRatingUnknownShouldReturnHttpStatusNotFound() {
        assertThat(movieController.getMovieByRating("Unknown").getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testSaveMovieShouldReturnHttpStatusOK() {
        Movie m = new Movie("title", "B", "action", "j", "2020");
        assertThat(movieController.saveMovie(m).getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testSaveExistingMovieShouldReturnHttpStatusNotAcceptable() {
        Movie m = new Movie("title", "C", "action", "j", "2020");
        movieController.saveMovie(m);
        assertThat(movieController.saveMovie(m).getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void testGetMovieById2ShouldReturnHttpStatusOK() {
        assertThat(movieController.showMovieById(2).getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testGetMovieByUnExistingIdShouldReturnHttpStatusNotFound() {
        assertThat(movieController.showMovieById(3000L).getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteMovieById5ShouldReturnHttpStatusOK() {
        Movie movie = new Movie();
        movieController.saveMovie(movie);
        assertThat(movieController.deleteMovie(movie.getId()).getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testDeleteMovieByUnExistingIdShouldReturnHttpStatusNotFound() {
        assertThat(movieController.deleteMovie(3000L).getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateExistingMovieShouldReturnHttpStatusOK() {
        Movie m = new Movie("title", "D", "action", "j", "2020");
        movieController.saveMovie(m);
        m.setYear("2021");
        assertThat(movieController.updatedMovie(m.getId(), m).getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testUpdateUnExistingMovieShouldReturnNotAcceptable() {
        Movie m = new Movie("title", "E", "action", "j", "2020");
        assertThat(movieController.updatedMovie(300L, m).getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void testDeleteDescriptionId2FromMovieId1ShouldReturnHttpStatusOK() {
        assertThat(movieController.deleteDescriptionsFromMovie(1L, 2L)
                .getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testDeleteUnExistingDescriptionIdFromMovieId1ShouldReturnHttpStatusNotFound() {
        assertThat(movieController.deleteDescriptionsFromMovie(1L, 5L)
                .getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetDescriptionFromMovieId1ShouldReturnHttpStatusOK() {
        assertThat(movieController.showDescriptionFromMovieById(1L)
                .getStatusCode())
                .isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testGetDescriptionFromMovieUnExistingIdShouldReturnHttpStatusNotFound() {
        assertThat(movieController.showDescriptionFromMovieById(200L)
                .getStatusCode())
                .isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }
}
