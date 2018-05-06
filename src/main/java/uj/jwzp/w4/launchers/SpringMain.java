package uj.jwzp.w4.launchers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uj.jwzp.w4.logic.CSVMovieFinder;
import uj.jwzp.w4.logic.MovieLister;
import uj.jwzp.w4.model.Movie;

@Slf4j
public class SpringMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("uj.jwzp.w4.logic");//uj.jwzp.w4.logic

        CSVMovieFinder movieFinder = (CSVMovieFinder) ctx.getBean("csvMovieFinder",  args[0]);
        MovieLister lister = (MovieLister) ctx.getBean("movieLister", movieFinder);

        lister.moviesDirectedBy("Hoffman").stream()
                .map(Movie::toString)
                .forEach(log::info);

    }
}
