package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.data.Movie;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public class RemoveGreaterCommand implements Command {
    private final MovieCollectionView movies;

    public RemoveGreaterCommand(MovieCollectionView movies) {
        this.movies = movies;
    }

    @Override
    public void apply(String[] args) throws ArgsException, Exception {
        if (args.length != 1) {
            throw new ArgsCountException(1, args.length);
        }

        int id;

        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new ArgsException("id must be a number");
        }
        
        Movie selected = movies.get(id);

        if (selected == null) {
            throw new Exception("Movie with id " + id + " not found");
        }

        movies.getAll().stream()
                .filter(movie -> movie.compareTo(selected) > 0)
                .toList()
                .forEach(movie -> movies.remove(movie.getId()));
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getArgs() {
        return "id";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, превышающие заданный";
    }

}
