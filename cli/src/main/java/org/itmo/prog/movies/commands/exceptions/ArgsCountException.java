package org.itmo.prog.movies.commands.exceptions;

public class ArgsCountException extends ArgsException {
    public ArgsCountException(int expected, int actual) {
        super(String.format("Expected %d arguments, got %d", expected, actual));
    }
}
