package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.commands.exceptions.ArgsException;

import java.io.BufferedWriter;
import java.io.IOException;

import org.itmo.prog.movies.commands.exceptions.ArgsCountException;

public final class HelpCommand implements Command {
    private final CommandHandler handler;
    private final BufferedWriter writer;

    public HelpCommand(CommandHandler handler, BufferedWriter writer) {
        this.handler = handler;
        this.writer = writer;
    }

    @Override
    public void apply(String[] args) throws ArgsException, IOException {
        if (args.length != 0)
            throw new ArgsCountException(0, args.length);
        writer.write("Help on all of the commands:\n");
        for (Command command : handler.getCommands()) {
            writer.write(command.getName() + " " + command.getArgs() + " - " + command.getDescription() + "\n");
            writer.flush();
        }
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
