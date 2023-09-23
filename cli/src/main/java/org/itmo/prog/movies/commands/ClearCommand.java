package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.views.MovieCollectionView;
import org.itmo.prog.movies.core.views.PersonCollectionView;

public class ClearCommand implements Command {
    private final MovieCollectionView movies;
    private final PersonCollectionView persons;

    public ClearCommand(MovieCollectionView movies, PersonCollectionView persons) {
        this.movies = movies;
        this.persons = persons;
    }

    public void apply(String[] args) throws ArgsException, Exception {
        if (args.length != 0) {
            throw new ArgsCountException(0, args.length);
        }
        movies.clear();
        persons.clear();
    }

    public String getName() {
        return "clear";
    }

    public String getArgs() {
        return "";
    }

    public String getDescription() {
        return "очистить коллекцию";
    }
}
