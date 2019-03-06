package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model){
        //actor creation
        Actor actor = new Actor();
        actor.setName("Sandra Bullock");
        actor.setRealname("Sandra Mae Bullowski");

        //movie creation
        Movie movie = new Movie();
        movie.setTitle("Emoji Movie ");
        movie.setYear(2017);
        movie.setDescription("About Emojis...");

        //add movie to empty list
        Set<Movie> movies = new HashSet<>();
        movies.add(movie);

        //save the list of movies to actors movie list
        actor.setMovies(movies);

        //save the actor to the database
        actorRepository.save(actor);

        //grade all the actors from the database and send them to template
        model.addAttribute("actors", actorRepository.findAll());
        return "index";
    }
}
