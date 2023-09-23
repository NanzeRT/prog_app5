package org.itmo.prog.movies.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.itmo.prog.movies.core.data.Movie;
import org.itmo.prog.movies.core.data.Person;

public class TwoCollections implements Serializable {
    public Collection<Movie> movies;
    public Collection<Person> persons;

    public TwoCollections(Collection<Movie> movies, Collection<Person> persons) {
        this.movies = movies;
        this.persons = persons;
    }

    public static TwoCollections collectFromMovies(Collection<Movie> movies) {
        Map<String, Person> persons = new HashMap<>();
        for (Movie movie : movies) {
            Person operator = movie.getOperator();
            if (operator.getPassportID() != null && !persons.containsKey(operator.getPassportID())) {
                persons.put(operator.getPassportID(), operator);
            }
        }
        return new TwoCollections(movies, persons.values());
    }
}
