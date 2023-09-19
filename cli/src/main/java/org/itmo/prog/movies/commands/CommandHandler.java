package org.itmo.prog.movies.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.itmo.prog.movies.commands.exceptions.CommandFormatException;

public final class CommandHandler {
    private Map<String, Command> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public void handle(String[] args) throws CommandFormatException {
        if (args.length == 0) {
            throw new CommandFormatException("No command provided");
        }
        String commandName = args[0];
        Command command = commands.get(commandName);
        if (command == null) {
            throw new CommandFormatException("Unknown command: " + commandName);
        }
        String[] commandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, commandArgs, 0, args.length - 1);
        command.apply(commandArgs);
    }

    public Collection<Command> getCommands() {
        return commands.values();
    }
}
