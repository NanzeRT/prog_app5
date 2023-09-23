package org.itmo.prog.movies.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.Nullable;

import org.itmo.prog.movies.cli.CommandSplitter;
import org.itmo.prog.movies.cli.PlainSplitter;
import org.itmo.prog.movies.cli.readers.MovieReader;
import org.itmo.prog.movies.cli.readers.NonInteractiveMovieReader;
import org.itmo.prog.movies.cli.readers.NonInteractivePersonReader;
import org.itmo.prog.movies.cli.readers.PersonReader;
import org.itmo.prog.movies.commands.exceptions.ArgsCountException;
import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.core.views.MovieCollectionView;
import org.itmo.prog.movies.core.views.PersonCollectionView;

public class ExecuteScriptCommand implements Command {
    private final MovieCollectionView movies;
    private final PersonCollectionView persons;
    private final String saveFileName;
    private final @Nullable BufferedWriter out;

    public ExecuteScriptCommand(MovieCollectionView movies, PersonCollectionView persons, String saveFileName, @Nullable BufferedWriter out) {
        this.movies = movies;
        this.persons = persons;
        this.saveFileName = saveFileName;
        this.out = out;
    }

    @Override
    public void apply(String[] args) throws ArgsException, Exception {
        if (args.length != 1) {
            throw new ArgsCountException(1, args.length);
        }
        BufferedReader in;
        try {
            in = Files.newBufferedReader(Path.of(args[0]), Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new Exception("Failed to open file: " + e.getMessage());
        }

        PersonReader personReader = new NonInteractivePersonReader(in, persons);
        MovieReader movieReader = new NonInteractiveMovieReader(personReader, in);
        CommandHandler commandHandler = new CommandHandler(null);

        commandHandler.register(this);
        commandHandler.register(new AddCommand(movieReader, movies));
        commandHandler.register(new UpdateCommand(movieReader, movies));
        commandHandler.register(new RemoveByIdCommand(movies));
        commandHandler.register(new ClearCommand(movies, persons));
        commandHandler.register(new SaveCommand(saveFileName, movies));
        commandHandler.register(new RemoveGreaterCommand(movies));

        CommandSplitter splitter = new PlainSplitter();

        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            if (out != null) {
                out.write(line);
                out.newLine();
                out.flush();
            }
            commandHandler.handle(splitter.split(line));
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getArgs() {
        return "file_name";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
