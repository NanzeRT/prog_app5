package org.itmo.prog.movies.commands;

import java.io.BufferedWriter;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.data.Movie;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public class ShowCommand implements Command {
    private final MovieCollectionView movies;
    private final BufferedWriter writer;

    public ShowCommand(MovieCollectionView movies, BufferedWriter writer) {
        this.movies = movies;
        this.writer = writer;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void apply(String[] args) throws ArgsException, Exception {
        if (args.length != 0) {
            throw new ArgsCountException(0, args.length);
        }
        try {
            for (Movie movie : movies.getAll()) {
                writer.write(movie.toString() + "\n");
            }
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
