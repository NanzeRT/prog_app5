package org.itmo.prog.movies.core;

import java.util.Collection;
import org.itmo.prog.movies.core.data.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fatboyindustrial.gsonjavatime.Converters;

public class JsonSerializer {
    private static final Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
    public static String serialize(Collection<Movie> movies) {
        return gson.toJson(movies);
    }

    public static TwoCollections deserialize(String json) {
        Movie[] movies = gson.fromJson(json, Movie[].class);
        return TwoCollections.collectFromMovies(java.util.Arrays.asList(movies));
    }
}
