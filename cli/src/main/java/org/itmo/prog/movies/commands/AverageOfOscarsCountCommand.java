package org.itmo.prog.movies.commands;

import java.io.BufferedWriter;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public class AverageOfOscarsCountCommand implements Command {
    private final MovieCollectionView movies;
    private final BufferedWriter out;

    public AverageOfOscarsCountCommand(MovieCollectionView movies, BufferedWriter out) {
        this.movies = movies;
        this.out = out;
    }

    @Override
    public void apply(String[] args) throws ArgsException, Exception {
        if (args.length != 0) {
            throw new ArgsCountException(0, args.length);
        }
        if (movies.getAll().isEmpty()) {
            out.write("Коллекция пуста");
            out.newLine();
            out.flush();
            return;
        }
        out.write(String.valueOf(movies.getAll().stream().mapToInt(movie -> movie.getOscarsCount()).average().getAsDouble()));
        out.newLine();
        out.flush();
    }

    @Override
    public String getName() {
        return "average_of_oscars_count";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public String getDescription() {
        return "вывести среднее значение поля oscarCount для всех элементов коллекции";
    }

}
