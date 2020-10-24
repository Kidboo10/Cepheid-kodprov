package com.cepheid.cloud.skel.servicetest;

import com.cepheid.cloud.skel.service.MovieService;
import com.cepheid.cloud.skel.TestBase;
import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Movie;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MovieServiceTest extends TestBase {


    @Autowired
    private MovieService movieService;

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Test
    public void testGetAllMovies() {
        Collection<Movie> movies = movieService.getMovies();
        assertEquals(movieService.getMovies().size(), movies.size());
    }

    @Test
    public void testYear3030ShouldReturn0Movies() {
        List<Movie> movie = movieService.getMovieByYear("3030");
        assertThat(movie.size()).isZero();
    }

    @Test
    public void testYear2019ShouldReturn3Movies() {
        List<Movie> movie = movieService.getMovieByYear("2019");
        assertThat(movie.size()).isEqualTo(3);
    }

    @Test
    public void testDirectorJordanPeeleShouldReturn2mMovies() {
        List<Movie> movie = movieService.getMovieByDirector("Jordan Peele");
        assertThat(movie.size()).isEqualTo(2);
    }

    @Test
    public void testUnknownDirectorShouldReturn0Movies() {
        List<Movie> movie = movieService.getMovieByDirector("Unknown");
        assertThat(movie.size()).isEqualTo(0);
    }

    @Test
    public void testRatingPG13ShouldReturn2Movies() {
        List<Movie> movie = movieService.getMovieByRating("pg-13");
        assertThat(movie.size()).isEqualTo(2);
    }

    @Test
    public void testRatingUnknownShouldReturn0Movies() {
        List<Movie> movie = movieService.getMovieByRating("Unknown");
        assertThat(movie.size()).isEqualTo(0);
    }

    @Test
    public void testSaveMovie() {
        Movie  m = new Movie("title", "B"
                , "action", "j", "2020");
        assertThat(movieService.saveMovie(m)).isNotNull();
        List<Movie> movie = movieService.getMovieByDirector("j");
        assertThat(m.getDirector()).isEqualTo(movie.get(0).getDirector());
    }

    @Test
    public void testGetMovieById2ShouldReturn1Movie() {
        Optional<Movie> movie = movieService.showMovieById(2L);
        assertThat(movie.get().getId()).isEqualTo(2L);
    }

    @Test
    public void testDeleteMovieById1ShouldRemoveId1Movie() {
        Movie movie2 = new Movie();
        movieService.saveMovie(movie2);
        movieService.deleteMovie(movie2.getId());
        Optional<Movie> checkMovie = movieService.showMovieById(movie2.getId());
        assertThat(checkMovie.isEmpty()).isTrue();
    }

    @Test
    public void testUpdateMovie() {
        Movie movie1 = new Movie("title", "B",
                "action", "j", "2020");
        assertThat(movieService.saveMovie(movie1)).isNotNull();
        List<Movie> movie = movieService.getMovieByYear("2020");
        assertThat(movie1.getYear()).isEqualTo(movie.get(0).getYear());
        movie1.setYear("2021");
        assertThat(movieService.updatedMovie(movie1.getId(), movie1)).isNotNull();
        movie = movieService.getMovieByYear("2021");
        assertThat(movie1.getYear()).isEqualTo(movie.get(0).getYear());
    }

    @Test
    public void testDeleteDescriptionId2ByMovieId1ShouldRemoveDescriptionId2() {
        Movie movie = new Movie();
        movieService.saveMovie(movie);
        Description description = new Description("A dream come true! "
                , "Believe the hype: This is a superhero flick unlike any we've ever seen, crafted by the unstoppable Ryan Coogler."
                , "2020", movie);
        descriptionRepository.save(description);
        movieService.deleteDescriptionsFromMovieId(movie.getId(), description.getId());
        List<Description> checkDescription = movieService.getDescriptionsFromMovieId(movie.getId());
        System.out.println(checkDescription.size());
        assertThat(checkDescription.isEmpty()).isTrue();
    }

}
