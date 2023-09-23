package org.itmo.prog.movies.commands;

import java.io.BufferedWriter;
import java.util.Arrays;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.data.MpaaRating;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public class FilterLessThanMpaaRatingCommand implements Command {
    private final MovieCollectionView movies;
    private final BufferedWriter out;

    public FilterLessThanMpaaRatingCommand(MovieCollectionView movies, BufferedWriter out) {
        this.movies = movies;
        this.out = out;
    }

    public void apply(String[] args) throws Exception {
        if (args.length != 1) {
            throw new ArgsCountException(1, args.length);
        }
        MpaaRating rating;
        try {
            rating = MpaaRating.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new ArgsException("mpaaRating mpaaRating must be one of " + Arrays.asList(MpaaRating.values()));
        }
        movies.getAll().stream()
                .filter(movie -> movie.getMpaaRating().compareTo(rating) < 0)
                .forEach(movie -> {
                    try {
                        out.write(movie.toString());
                        out.newLine();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        out.flush();
    }

    public String getName() {
        return "filter_less_than_mpaa_rating";
    }

    public String getArgs() {
        return "mpaaRating";
    }

    public String getDescription() {
        return "вывести элементы, значение поля mpaaRating которых меньше заданного";
    }
}
