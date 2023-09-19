package org.itmo.prog.movies.commands.exceptions;

public class CommandFormatException extends Exception {
    public CommandFormatException(String message) {
        super(message);
    }
}
