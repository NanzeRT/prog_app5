package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.commands.exceptions.ArgsException;
import org.itmo.prog.movies.commands.exceptions.ArgsCountException;

public final class HelpCommand implements Command {
    private final CommandHandler handler;

    public HelpCommand(CommandHandler handler) {
        this.handler = handler;
    }

    @Override
    public void apply(String[] args) throws ArgsException {
        if (args.length != 0)
            throw new ArgsCountException(0, args.length);
        handler.getCommands().forEach(command -> System.out.println(command.getName() + " " + command.getArgs() + " - " + command.getDescription()));
        // TODO: вывести
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

}
