package org.itmo.prog.movies.commands;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.JsonSerializer;
import org.itmo.prog.movies.core.views.MovieCollectionView;

public class SaveCommand implements Command {
    private final String path;
    private final MovieCollectionView movies;

    public SaveCommand(String path, MovieCollectionView movies) {
        this.path = path;
        this.movies = movies;
    }

    @Override
    public void apply(String[] args) throws ArgsException, Exception {
        if (args.length != 0) {
            throw new ArgsCountException(0, args.length);
        }
        String json = JsonSerializer.serialize(movies.getAll());
        try {
            if (Files.exists(Path.of(path))) {
                if (Files.exists(Path.of(path + ".bak")))
                    Files.delete(Path.of(path + ".bak"));
                Files.move(Path.of(path), Path.of(path + ".bak"));
            }
            Files.writeString(Path.of(path), json, Charset.forName("UTF-8"));
        } catch (Exception e) {
            throw new Exception("Failed to write file: " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

}
