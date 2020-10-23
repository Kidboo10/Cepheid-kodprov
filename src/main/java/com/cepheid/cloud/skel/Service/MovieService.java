package com.cepheid.cloud.skel.Service;

import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Movie;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import com.cepheid.cloud.skel.repository.MovieRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Component
@Api()
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final DescriptionRepository descriptionRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, DescriptionRepository descriptionRepository) {
        this.movieRepository = movieRepository;
        this.descriptionRepository = descriptionRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public List<Description> getDescriptionsFromMovieId(long id) {
        Optional<Movie> movieId = movieRepository.findById(id);
        if (movieId.isPresent())
            return movieId.get().getDescriptions();

        return null;
    }

    public void deleteDescriptionsFromMovieId(Long movieId, Long descId) {
        Optional<Movie> movieDesc = movieRepository.findById(movieId);
        List<Description> descriptions = null;
        if (movieDesc.isPresent())
            descriptions = movieDesc.get().getDescriptions();


        for (Description description : descriptions) {
            if (description.getId().equals(descId))
                descriptionRepository.deleteById(descId);
        }
    }

    public List<Movie> getMovieByYear(String year) {
        return movieRepository.findMovieByYear(year);
    }

    public List<Movie> getMovieByDirector(String director) {
        return movieRepository.findMovieByDirector(director);
    }

    public List<Movie> getMovieByRating(String rating) {
        return movieRepository.findMovieByRating(rating);
    }

    public Object saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> showMovieById(long id) {
        return movieRepository.findById(id);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Object updatedMovie(long id, Movie movie) {
        movie.setId(id);
        return movieRepository.save(movie);
    }
}


