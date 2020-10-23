package com.cepheid.cloud.skel.MockDb;

import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Movie;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import com.cepheid.cloud.skel.repository.MovieRepository;

public class MockDB {
    public void creatMockData(MovieRepository movieRepository, DescriptionRepository descriptionRepository) {

        Movie movie2 = new Movie("BLACK PANTHER", "PG-13",
                "Adventure-Action", "Ryan Coogler", "2018");
        Movie movie3 = new Movie("AVENGERS: ENDGAME", "PG-13",
                "Adventure-Action", "Anthony Russo", "2019");
        Movie movie4 = new Movie("US", "R", "Horror",
                "Jordan Peele", "2019");
        Movie movie5 = new Movie("TOY STORY 4", "G",
                "Animation", "Josh Cooley", "2019");
        Movie movie6 = new Movie("GET OUT", "R",
                "Horror", "Jordan Peele", "2017");

        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
        movieRepository.save(movie5);
        movieRepository.save(movie6);

        Description m2Description = new Description("A dream come true! "
                , "Believe the hype: This is a superhero flick unlike any we've ever seen, crafted by the unstoppable Ryan Coogler."
                , "2020", movie2);

        Description m2Description2 = new Description("Marvel's most politically inspired", "\n" +
                "Black Panther not only stands up as a Hollywood blockbuster but conveys an adult and chronological context that touches upon slavery," +
                " the Civil Rights movement" +
                ", Black Lives Matter and the current African-American plight."
                , "2020", movie2);

        Description m3Description = new Description("The only complaint about Avengers: Endgame"
                , "It raises the bar so high that there may well never be a superhero movie to match it."
                , "2019", movie3);

        Description m3Description2 = new Description("What's missing from \"Endgame\" "
                , "The free play of imagination, the liberation of speculation," +
                " the meandering paths and loose ends that start in logic and lead to wonder."
                , "2019", movie3);

        Description m4Description = new Description("Peele develops a genuinely thrilling",
                "Heart-in-the-throat-scary horror picture.", "2019", movie4);

        Description m4Description2 = new Description("I love the performances",
                "But I'm just not super sold on the script, to be honest... " +
                        "I'm really ready for [Jordan Peele] to be his own voice now.", "2019", movie4);

        Description m5Description = new Description("One of the most admirable things "
                , "Toy Story 4 is refusal to bow to the easy call of nostalgia.", "2020", movie5);

        Description m5Description2 = new Description("Toy Story 4 proves there's still life in these toys in an emotionally heartfelt tale. "
                , "I'm not crying. You're crying.", "2020", movie5);

        Description m6Description = new Description("Peele seduces, subverts and manipulates audience expectations",
                "As the masters Alfred Hitchcock, John Carpenter, and Stanley Kubrick did before him."
                , "2020", movie6);

        Description m6Description2 = new Description("It's a game-changer.",
                "Kudos to Peele for tackling a painful subject in such a massively entertaining and thoroughly memorable fashion."
                , "2017", movie6);

        descriptionRepository.save(m2Description);
        descriptionRepository.save(m2Description2);
        descriptionRepository.save(m3Description);
        descriptionRepository.save(m3Description2);
        descriptionRepository.save(m4Description);
        descriptionRepository.save(m4Description2);
        descriptionRepository.save(m5Description);
        descriptionRepository.save(m5Description2);
        descriptionRepository.save(m6Description);
        descriptionRepository.save(m6Description2);

        movieRepository.findAll().forEach(System.out::println);
    }
}