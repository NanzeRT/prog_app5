package org.itmo.prog.movies.commands;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.itmo.prog.movies.commands.exceptions.CommandFormatException;

public final class CommandHandler {
    private Map<String, Command> commands = new HashMap<>();
    private final @Nullable BufferedWriter errorWriter;
    private final List<Command> history = new ArrayList<>();
    static final int HISTORY_SIZE = 13;

    public CommandHandler(@Nullable BufferedWriter errorWriter) {
        this.errorWriter = errorWriter;
    }

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public void tryHandle(String[] args) {
        try {
            handle(args);
        } catch (Exception e) {
            if (errorWriter == null)
                return;
            try {
                errorWriter.write(e.getMessage() + "\n");
                errorWriter.flush();
            } catch(IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public void handle(String[] args) throws CommandFormatException, Exception {
        if (args.length == 0) {
            throw new CommandFormatException("No command provided");
        }
        String commandName = args[0];
        Command command = commands.get(commandName);
        addHistory(command);
        if (command == null) {
            throw new CommandFormatException("Unknown command: " + commandName);
        }
        String[] commandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, commandArgs, 0, args.length - 1);
        command.apply(commandArgs);
    }

    private void addHistory(Command command) {
        history.add(command);
        if (history.size() > HISTORY_SIZE) {
            history.remove(0);
        }
    }

    public Collection<Command> getCommands() {
        return commands.values();
    }

    public List<Command> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
