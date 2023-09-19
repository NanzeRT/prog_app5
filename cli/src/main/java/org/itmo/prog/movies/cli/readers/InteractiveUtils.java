package org.itmo.prog.movies.cli.readers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

class InteractiveUtils {
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public InteractiveUtils(BufferedReader reader, @Nullable BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public @Nullable <T> T readNullable(String prompt, Function<String, T> parser) throws IOException {
        if (writer != null) {
            writer.write(prompt);
            writer.flush();
        }
        while (true) {
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
                if (writer != null) {
                    writer.write("Invalid input: " + e.getMessage() + "\n");
                    writer.flush();
                }
            }
        }
    }

    public <T> T readNonNull(String prompt, Function<String, T> parser) throws IOException {
        while (true) {
            T result = readNullable(prompt, parser);
            if (result != null) {
                return result;
            }
            if (writer != null) {
                writer.write("Input cannot be null\n");
                writer.flush();
            }
        }
    }

    public <T> void setNullable(String prompt, Function<String, T> parser, Consumer<T> setter) throws IOException {
        while (true) {
            T result = readNullable(prompt, parser);
            try {
                setter.accept(result);
                return;
            } catch (Exception e) {
                if (writer != null) {
                    writer.write("Invalid input: " + e.getMessage() + "\n");
                    writer.flush();
                }
            }
        }
    }

    public <T> void setNonNull(String prompt, Function<String, T> parser, Consumer<T> setter) throws IOException {
        while (true) {
            T result = readNonNull(prompt, parser);
            try {
                setter.accept(result);
                return;
            } catch (Exception e) {
                if (writer != null) {
                    writer.write("Invalid input: " + e.getMessage() + "\n");
                    writer.flush();
                }
            }
        }
    }
}
