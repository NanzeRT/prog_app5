package org.itmo.prog.movies.cli.readers;

import java.io.IOException;

import org.itmo.prog.movies.core.data.Movie;

public interface MovieReader {
    public Movie.Creator read() throws IOException;
}
