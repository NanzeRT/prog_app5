package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.cli.readers.MovieReader;
import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public final class AddCommand implements Command {

    private final MovieCollectionView movies;
    private final MovieReader reader;

    public AddCommand(MovieReader reader, MovieCollectionView movies) {
        this.reader = reader;
        this.movies = movies;
    }

    @Override
    public void apply(String[] args) throws ArgsCountException, Exception {
        if (args.length != 0)
            throw new ArgsCountException(0, args.length);
        movies.add(reader.read());
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}
