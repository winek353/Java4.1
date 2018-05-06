package uj.jwzp.w4.logic;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.simpleflatmapper.csv.CsvParser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import uj.jwzp.w4.model.Movie;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uj.jwzp.w4.tools.NullReader.nullReader;

@Slf4j
@Service("csvMovieFinder")
@Scope("prototype")
public class CSVMovieFinder implements MovieFinder {
    private final List<Movie> allMovies;

    public CSVMovieFinder(String FILE_NAME) {
        Reader reader = Try
                .of(() -> (Reader) new FileReader(FILE_NAME))
                .onFailure(ex -> log.error("Cannot read file" + FILE_NAME, ex))
                .getOrElse(nullReader());
        allMovies = Try
                .of(() -> CsvParser.mapTo(Movie.class).stream(reader))
                .onFailure(ex -> log.error("Error while processing file " + FILE_NAME, ex))
                .getOrElse(Stream.empty())
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(allMovies);
    }
}
