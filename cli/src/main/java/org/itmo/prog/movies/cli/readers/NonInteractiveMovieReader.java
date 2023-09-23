package org.itmo.prog.movies.cli.readers;

import org.itmo.prog.movies.core.data.Movie;
import org.itmo.prog.movies.core.data.Coordinates;
import org.itmo.prog.movies.core.data.MovieGenre;
import org.itmo.prog.movies.core.data.MpaaRating;

import java.io.BufferedReader;
import java.io.IOException;

public class NonInteractiveMovieReader implements MovieReader {
    private final PersonReader personReader;
    private final BufferedReader reader;

    public NonInteractiveMovieReader(
            PersonReader personReader, BufferedReader reader) {
        this.personReader = personReader;
        this.reader = reader;
    }

    @Override
    public Movie.Creator read() throws IOException {
        NonInteractiveUtils utils = new NonInteractiveUtils(reader);
        Movie.Creator creator = new Movie.Creator();
        creator.setName(utils.readNonNull(s -> s));
        creator.setCoordinates(createCoordinates());
        creator.setOscarsCount(utils.readNonNull(Integer::parseInt));
        creator.setGenre(utils.readNonNull(MovieGenre::valueOf));
        creator.setMpaaRating(utils.readNullable(MpaaRating::valueOf));
        creator.setOperator(personReader.getOrInsert());
        return creator;
    }

    private Coordinates createCoordinates() throws IOException {
        NonInteractiveUtils utils = new NonInteractiveUtils(reader);
        Coordinates.Creator creator = new Coordinates.Creator();
        creator.setX(utils.readNonNull(Double::parseDouble));
        creator.setY(utils.readNonNull(Float::parseFloat));
        return creator.create();
    }
}
