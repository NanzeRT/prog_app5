package org.itmo.prog.movies.commands;

import java.io.BufferedWriter;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.views.MovieCollectionView;
import org.itmo.prog.movies.core.views.PersonCollectionView;

public class InfoCommand implements Command {
    private final MovieCollectionView movies;
    private final PersonCollectionView persons;
    private final BufferedWriter writer;

    public InfoCommand(MovieCollectionView movies, PersonCollectionView persons, BufferedWriter writer) {
        this.movies = movies;
        this.persons = persons;
        this.writer = writer;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public void apply(String[] args) throws ArgsException {
        if (args.length != 0) {
            throw new ArgsCountException(0, args.length);
        }
        try {
            writer.write("Тип коллекции: " + movies.getClass().getSimpleName() + "\n");
            writer.write("Количество элементов: " + movies.size() + "\n");
            writer.write("Дата инициализации: " + movies.getInitializationDate() + "\n");
            writer.newLine();
            writer.write("Тип коллекции: " + persons.getClass().getSimpleName() + "\n");
            writer.write("Количество элементов: " + persons.size() + "\n");
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
