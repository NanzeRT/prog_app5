package org.itmo.prog.movies.commands;

import java.io.IOException;

import org.itmo.prog.movies.cli.readers.MovieReader;
import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.data.Movie;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public final class UpdateCommand implements Command {

    private final MovieCollectionView movies;
    private final MovieReader reader;

    public UpdateCommand(MovieReader reader, MovieCollectionView movies) {
        this.reader = reader;
        this.movies = movies;
    }

    @Override
    public void apply(String[] args) throws ArgsException, IOException {
        if (args.length != 1)
            throw new ArgsCountException(1, args.length);
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new ArgsException("id должен быть числом");
        }
        Movie.Creator creator = reader.read();
        creator.setId(id);
        movies.update(creator);
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getArgs() {
        return "id";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

}
