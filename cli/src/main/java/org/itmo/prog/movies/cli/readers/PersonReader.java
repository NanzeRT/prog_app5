package org.itmo.prog.movies.cli.readers;

import java.io.IOException;

import org.itmo.prog.movies.core.data.Person;

public interface PersonReader {
    public Person create() throws IOException;
    public Person getOrInsert() throws IOException;
}
