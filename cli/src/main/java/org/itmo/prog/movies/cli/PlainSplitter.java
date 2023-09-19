package org.itmo.prog.movies.cli;

public class PlainSplitter implements CommandSplitter {
    public String[] split(String command) {
        return command.split(" ");
    }
}
