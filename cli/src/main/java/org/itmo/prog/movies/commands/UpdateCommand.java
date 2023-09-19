package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.readers.MovieReader;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public final class UpdateCommand implements Command {

    private MovieCollectionView movies;
    private MovieReader reader;

    @Override
    public void apply(String[] args) throws ArgsException {
        if (args.length != 1)
            throw new ArgsCountException(1, args.length);
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new ArgsException("id должен быть числом");
        }
        movies.update(reader.create(id));
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
