package org.itmo.prog.movies.commands;

import java.io.BufferedWriter;

public class HistoryCommand implements Command {
    private final CommandHandler handler;
    private final BufferedWriter out;

    public HistoryCommand(CommandHandler handler, BufferedWriter out) {
        this.handler = handler;
        this.out = out;
    }

    @Override
    public void apply(String[] args) throws Exception {
        for (Command command : handler.getHistory()) {
            out.write(command.getName());
            out.newLine();
        }
    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public String getDescription() {
        return "вывести историю команд";
    }
}
