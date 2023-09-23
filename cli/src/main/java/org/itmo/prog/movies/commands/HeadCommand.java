package org.itmo.prog.movies.commands;

import java.io.BufferedWriter;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public class HeadCommand implements Command {
    private final MovieCollectionView movies;
    private final BufferedWriter out;

    public HeadCommand(MovieCollectionView movies, BufferedWriter out) {
        this.movies = movies;
        this.out = out;
    }

    @Override
    public void apply(String[] args) throws ArgsException, Exception {
        if (args.length != 0) {
            throw new ArgsCountException(0, args.length);
        }
        if (movies.getAll().isEmpty()) {
            out.write("Collection is empty");
            out.newLine();
            return;
        }
        out.write(movies.getAll().get(0).toString());
        out.newLine();
    }

    @Override
    public String getName() {
        return "head";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public String getDescription() {
        return "вывести первый элемент коллекции";
    }

}
