package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public final class RemoveByIdCommand implements Command {
    private final MovieCollectionView movies;

    public RemoveByIdCommand(MovieCollectionView movies) {
        this.movies = movies;
    }

    @Override
    public void apply(String[] args) throws ArgsException {
        if (args.length != 1)
            throw new ArgsException("Неверное количество аргументов");
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new ArgsException("id должен быть числом");
        }
        movies.remove(id);
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
    
    @Override
    public String getArgs() {
        return "id";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }
}
