package org.itmo.prog.movies.cli.readers;

import org.itmo.prog.movies.core.data.Movie;
import org.itmo.prog.movies.core.data.Coordinates;
import org.itmo.prog.movies.core.data.MovieGenre;
import org.itmo.prog.movies.core.data.MpaaRating;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Nullable;

public class InteractiveMovieReader implements MovieReader {
    private final PersonReader personReader;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public InteractiveMovieReader(
            PersonReader personReader, BufferedReader reader, @Nullable BufferedWriter writer) {
        this.personReader = personReader;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public Movie.Creator read() throws IOException {
        InteractiveUtils utils = new InteractiveUtils(reader, writer);
        Movie.Creator creator = new Movie.Creator();
        utils.setNonNull("Enter movie name (String): ", s -> s, creator::setName);
        writer.write("Entering coordinates:\n");
        creator.setCoordinates(createCoordinates());
        writer.write("Exiting coordinates\n");
        utils.setNonNull("Enter oscars count (int): ", Integer::parseInt, creator::setOscarsCount);
        utils.setNonNull("Possible genres: " + Arrays.asList(MovieGenre.values()) + "\nEnter genre (MovieGenre): ", MovieGenre::valueOf, creator::setGenre);
        utils.setNullable("Possible ratings: " + Arrays.asList(MpaaRating.values()) + "\nEnter rating (MpaaRating or empty): ", MpaaRating::valueOf, creator::setMpaaRating);
        writer.write("Entering operator:\n");
        creator.setOperator(personReader.getOrInsert());
        writer.write("Exiting operator\n");
        return creator;
    }

    private Coordinates createCoordinates() throws IOException {
        InteractiveUtils utils = new InteractiveUtils(reader, writer);
        Coordinates.Creator creator = new Coordinates.Creator();
        utils.setNonNull("Enter x (double): ", Double::parseDouble, creator::setX);
        utils.setNonNull("Enter y (float): ", Float::parseFloat, creator::setY);
        return creator.create();
    }
}
