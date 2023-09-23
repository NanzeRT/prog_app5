package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.cli.App;

public class ExitCommand implements Command {
    private final App app;

    public ExitCommand(App app) {
        this.app = app;
    }
    
    @Override
    public void apply(String[] args) {
        app.exit();
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }
}
