package org.itmo.prog.movies.commands;

import java.io.BufferedWriter;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public class PrintDescendingCommand implements Command {
    private final MovieCollectionView movies;
    private final BufferedWriter out;

    public PrintDescendingCommand(MovieCollectionView movies, BufferedWriter out) {
        this.movies = movies;
        this.out = out;
    }

    public void apply(String[] args) throws Exception {
        if (args.length != 0) {
            throw new ArgsCountException(0, args.length);
        }

        movies.getAll().stream()
                .sorted((a, b) -> b.compareTo(a))
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
        return "print_descending";
    }

    public String getArgs() {
        return "";
    }

    public String getDescription() {
        return "вывести элементы коллекции в порядке убывания";
    }
}
