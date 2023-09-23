package org.itmo.prog.movies.cli;

import org.itmo.prog.movies.cli.readers.InteractiveMovieReader;
import org.itmo.prog.movies.cli.readers.InteractivePersonReader;
import org.itmo.prog.movies.cli.readers.MovieReader;
import org.itmo.prog.movies.cli.readers.PersonReader;
import org.itmo.prog.movies.commands.AddCommand;
import org.itmo.prog.movies.commands.AverageOfOscarsCountCommand;
import org.itmo.prog.movies.commands.ClearCommand;
import org.itmo.prog.movies.commands.CommandHandler;
import org.itmo.prog.movies.commands.ExecuteScriptCommand;
import org.itmo.prog.movies.commands.ExitCommand;
import org.itmo.prog.movies.commands.FilterLessThanMpaaRatingCommand;
import org.itmo.prog.movies.commands.HelpCommand;
import org.itmo.prog.movies.commands.HistoryCommand;
import org.itmo.prog.movies.commands.InfoCommand;
import org.itmo.prog.movies.commands.PrintDescendingCommand;
import org.itmo.prog.movies.commands.RemoveByIdCommand;
import org.itmo.prog.movies.commands.RemoveGreaterCommand;
import org.itmo.prog.movies.commands.SaveCommand;
import org.itmo.prog.movies.commands.ShowCommand;
import org.itmo.prog.movies.commands.UpdateCommand;
import org.itmo.prog.movies.core.JsonSerializer;
import org.itmo.prog.movies.core.TwoCollections;
import org.itmo.prog.movies.core.views.MovieCollectionView;
import org.itmo.prog.movies.core.views.MovieLinkedListView;
import org.itmo.prog.movies.core.views.PersonCollectionView;
import org.itmo.prog.movies.core.views.PersonLinkedListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) {
        new App().run(args);
    }

    private boolean exited = false;

    public void run(String[] args) {
        String saveFilaName = args.length > 0 ? args[0] : "movies.json";
        TwoCollections collections = null;
        try {
            collections = readCollections(saveFilaName);
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
            System.out.println("Failed to read file, creating new collections");
            System.out.println("You may want to exit before saving to avoid overwriting file");
            System.out.println("Warning: `save` command may not work correctly");
            collections = new TwoCollections(List.of(), List.of());
        } catch (Exception e) {
            System.err.println("Failed to parse file: " + e.getMessage());
            return;
        }


        PersonCollectionView persons = new PersonLinkedListView(collections.persons);
        MovieCollectionView movies = new MovieLinkedListView(collections.movies);
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedWriter cerr = new BufferedWriter(new OutputStreamWriter(System.err));
        PersonReader personReader = new InteractivePersonReader(cin, cout, persons);
        MovieReader movieReader = new InteractiveMovieReader(personReader, cin, cout);
        CommandHandler commandHandler = new CommandHandler(cerr);


        commandHandler.register(new HelpCommand(commandHandler, cout));
        commandHandler.register(new InfoCommand(movies, persons, cout));
        commandHandler.register(new ShowCommand(movies, cout));
        commandHandler.register(new AddCommand(movieReader, movies));
        commandHandler.register(new UpdateCommand(movieReader, movies));
        commandHandler.register(new RemoveByIdCommand(movies));
        commandHandler.register(new ClearCommand(movies, persons));
        commandHandler.register(new SaveCommand(saveFilaName, movies));
        commandHandler.register(new ExecuteScriptCommand(movies, persons, saveFilaName, cout));
        commandHandler.register(new ExitCommand(this));
        commandHandler.register(new HelpCommand(commandHandler, cout));
        commandHandler.register(new RemoveGreaterCommand(movies));
        commandHandler.register(new HistoryCommand(commandHandler, cout));
        commandHandler.register(new AverageOfOscarsCountCommand(movies, cout));
        commandHandler.register(new FilterLessThanMpaaRatingCommand(movies, cout));
        commandHandler.register(new PrintDescendingCommand(movies, cout));

        CommandSplitter splitter = new PlainSplitter();

        while (!exited) {
            String command;
            try {
                cout.write("> ");
                cout.flush();
                command = cin.readLine();
            } catch (IOException e) {
                System.err.println("Failed to read command: " + e.getMessage());
                break;
            }
            if (command == null)
                break;

            commandHandler.tryHandle(splitter.split(command));
        }
    }

    public void exit() {
        exited = true;
    }

    private TwoCollections readCollections(String fileName) throws IOException {
        String json = Files.readString(Path.of(fileName), Charset.forName("UTF-8"));
        return JsonSerializer.deserialize(json);
    }
}
