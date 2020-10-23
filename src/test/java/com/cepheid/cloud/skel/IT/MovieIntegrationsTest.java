package com.cepheid.cloud.skel.IT;

import com.cepheid.cloud.skel.TestBase;
import com.cepheid.cloud.skel.controller.MovieController;
import com.cepheid.cloud.skel.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MovieIntegrationsTest extends TestBase {

    @Autowired
    private MovieController mMovieController;

    @Test
    public void testGetItems() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies");

        Response response = movieController.get(new GenericType<Response>() {
        });
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testGetYear3030ShouldReturnHttpStatusNotFound() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies/get/year?year='3030'");
        Response response = movieController.get(new GenericType<Response>() {
        });
        ResponseEntity<Object> movie = mMovieController.getMovieByYear("3030");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        assertThat(movie.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetYear2019ShouldReturnHttpStatusOK() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies/get/year?year='2019'");
        Response response = movieController.get(new GenericType<Response>() {
        });
        ResponseEntity<Object> movie = mMovieController.getMovieByYear("2019");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        assertThat(movie.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testGetUnknownDirectorShouldReturnHttpStatusNotFound() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies/get/director?director='Unknown'");
        Response response = movieController.get(new GenericType<Response>() {
        });
        ResponseEntity<Object> movie = mMovieController.getMovieByDirector("Unknown");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        assertThat(movie.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetRatingPG13ShouldReturnHttpStatusOK() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies/get/rating?rating='pg-13'");
        Response response = movieController.get(new GenericType<Response>() {
        });
        ResponseEntity<Object> movie = mMovieController.getMovieByRating("pg-13");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        assertThat(movie.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testGetRatingUnknownShouldReturnHttpStatusNotFound() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies/get/rating?rating='Unknown'");
        Response response = movieController.get(new GenericType<Response>() {
        });
        ResponseEntity<Object> movie = mMovieController.getMovieByRating("Unknown");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        assertThat(movie.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetMovieById2ShouldReturnHttpStatusOK() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies/get/2");
        Response response = movieController.get(new GenericType<Response>() {
        });
        ResponseEntity<Object> movie = mMovieController.showMovieById(2L);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        assertThat(movie.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }

    @Test
    public void testGetMovieByUnExistingIdShouldReturnHttpStatusNotFound() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies/get/200");
        Response response = movieController.get(new GenericType<Response>() {
        });
        ResponseEntity<Object> movie = mMovieController.showMovieById(200L);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        assertThat(movie.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);

    }

    @Test
    public void testDeleteMovieByUnExistingIdShouldReturnHttpStatusNotFound() {
        Invocation.Builder movieController = getBuilder("/app/api/1.0/movies/delete/200");
        Response response = movieController.get(new GenericType<Response>() {
        });
        ResponseEntity<Object> movie = mMovieController.deleteMovie(200L);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.METHOD_NOT_ALLOWED);
        assertThat(movie.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }
}
