package org.itmo.prog.movies.cli.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;

class NonInteractiveUtils {
    private final BufferedReader reader;

    public NonInteractiveUtils(BufferedReader reader) {
        this.reader = reader;
    }

    public <T> T readNullable(Function<String, T> parser) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            throw new IOException("Unexpected end of input");
        }
        if (line == "") {
            return null;
        }
        try {
            return parser.apply(line);
        } catch (Exception e) {
            throw new IOException("Invalid input: " + e.getMessage());
        }
    }

    public <T> T readNonNull(Function<String, T> parser) throws IOException {
        T result = readNullable(parser);
        if (result != null) {
            return result;
        }
        throw new IOException("Input cannot be null");
    }
}
