package uj.jwzp.w4.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import uj.jwzp.w4.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Service("movieLister")
@Scope("prototype")
public class MovieLister {
    private MovieFinder finder; //= new CSVMovieFinder("movies.txt");

    public MovieLister(MovieFinder finder) {
        this.finder = finder;
    }

    public List<Movie> moviesDirectedBy(String name) {
        List<Movie> allMovies = finder.findAll();
        return allMovies.stream()
                .filter(m -> m.getDirector().equals(name))
                .collect(Collectors.toList());
    }
}
