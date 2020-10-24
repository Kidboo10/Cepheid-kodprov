package com.cepheid.cloud.skel.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.cepheid.cloud.skel.service.MovieService;
import com.cepheid.cloud.skel.model.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cepheid.cloud.skel.model.Movie;
import io.swagger.annotations.Api;

@Path("/api/1.0/movies")
@Api()
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(propagation = Propagation.REQUIRED)
@Controller
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService mMovieService) {
        this.movieService = mMovieService;
    }

    @GET
    public Collection<Movie> getAllMovies() {
        return movieService.getMovies();
    }

    @GET
    @Path("get/year")
    public ResponseEntity<Object> getMovieByYear(@QueryParam("year") String year) {
        List<Movie> movieList = movieService.getMovieByYear(year);
        if (!movieList.isEmpty())
            return ResponseEntity.ok(movieList);

        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GET
    @Path("get/director")
    public ResponseEntity<Object> getMovieByDirector(@QueryParam("director") String director) {
        List<Movie> movieList = movieService.getMovieByDirector(director);
        if (!movieList.isEmpty())
            return ResponseEntity.ok(movieList);

        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GET
    @Path("get/rating")
    public ResponseEntity<Object> getMovieByRating(@QueryParam("rating") String rating) {
        List<Movie> movieList = movieService.getMovieByRating(rating);
        if (!movieList.isEmpty())
            return ResponseEntity.ok(movieList);

        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @POST
    @Path("/add")
    public ResponseEntity<Object> saveMovie(Movie movie) {
        if (movie.getId() == null)
            return ResponseEntity.ok(movieService.saveMovie(movie));

        return new ResponseEntity<>("Did not save", HttpStatus.NOT_ACCEPTABLE);
    }

    @GET
    @Path("/get/{id}")
    public ResponseEntity<Object> showMovieById(@PathParam("id") long id) {
        Optional<Movie> movie = movieService.showMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie);

        }
        return new ResponseEntity<>("Did not found movie", HttpStatus.NOT_FOUND);
    }

    @DELETE
    @Path("/delete/{id}")
    public ResponseEntity<Object> deleteMovie(@PathParam("id") Long id) {
        Optional<Movie> movie = movieService.showMovieById(id);
        if (movie.isPresent()) {
            movieService.deleteMovie(id);
            return new ResponseEntity<>("Deleted: " + movie, HttpStatus.OK);
        }
        return new ResponseEntity<>("Did not found movie to delete", HttpStatus.NOT_FOUND);
    }

    @DELETE
    @Path("/delete/description/movieId/{movieId}/descriptionId/{descId}")
    public ResponseEntity<Object> deleteDescriptionsFromMovie(@PathParam("movieId") Long movieId,
                                                              @PathParam("descId") Long descId) {

        Optional<Movie> movie = movieService.showMovieById(movieId);
        List<Description> desc = movieService.getDescriptionsFromMovieId(movieId);
        try {
            if (movie.isEmpty() && desc.isEmpty())
                return new ResponseEntity<>("Did not found movie delete", HttpStatus.NOT_FOUND);


            for (Description description : desc) {
                Long description1 = description.getId();
                if (description1.equals(descId) && movie.isPresent()) {
                    movieService.deleteDescriptionsFromMovieId(movieId, descId);
                    return new ResponseEntity<>("description from  movie deleted: " + desc, HttpStatus.OK);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Did not found movie/description to delete");
        }
        return new ResponseEntity<>("Did not found movie/description to delete", HttpStatus.NOT_FOUND);
    }

    @PUT
    @Path("/put/{id}")
    public ResponseEntity<Object> updatedMovie(@PathParam("id") long id, Movie movie) {
        movie.setId(id);
        Optional<Movie> checkId = movieService.showMovieById(id);
        if (checkId.isEmpty())
            return new ResponseEntity<>("Did not updated", HttpStatus.NOT_ACCEPTABLE);

        return ResponseEntity.ok(movieService.updatedMovie(id, movie));
    }

    @GET
    @Path("/get/descriptions/{id}")
    public ResponseEntity<Object> showDescriptionFromMovieById(@PathParam("id") long id) {
        List<Description> movie = movieService.getDescriptionsFromMovieId(id);
        try {
            if (movie == null)
                return new ResponseEntity<>("Did not found movie description", HttpStatus.NOT_FOUND);

        } catch (NullPointerException e) {
            System.out.println("Did not found description for movie ");
        }
        return ResponseEntity.ok(movie);
    }
}
