package org.itmo.prog.movies.commands;

import org.itmo.prog.movies.commands.exceptions.ArgsException;

public interface Command {
    public void apply(String[] args) throws ArgsException;
    public String getName();
    public String getArgs();
    public String getDescription();
}
