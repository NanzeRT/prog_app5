package org.itmo.prog.movies.core.views;

import org.itmo.prog.movies.core.data.Movie;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

public final class MovieLinkedListView implements MovieCollectionView {
    private final List<Movie> movies;
    private int maxId = 0;

    public MovieLinkedListView(Collection<Movie> movies) {
        this.movies = new LinkedList<>(movies);
        for (Movie movie : movies) maxId = Math.max(maxId, movie.getId());
    }

    public MovieLinkedListView() {
        this(List.of());
    }

    @Override
    public @Nullable Movie get(int id) {
        return movies.stream().filter(movie -> movie.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Movie> getAll() {
        return Collections.unmodifiableList(movies);
    }

    @Override
    public Movie add(Movie movie) {
        return add(Movie.Creator.from(movie));
    }

    @Override
    public Movie add(Movie.Creator creator) {
        creator.setId(++maxId);
        creator.setCreationDate(LocalDateTime.now());
        Movie movie = creator.create();
        if (!movies.add(movie)) return null;
        return movie;
    }

    @Override
    public void remove(int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }

    @Override
    public Movie update(Movie movie) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId().intValue() == movie.getId().intValue()) {
                movies.set(i, movie);
                return movie;
            }
        }
        return null;
    }

    @Override
    public Movie update(Movie.Creator creator) {
        return update(creator.create());
    }

    @Override
    public void clear() {
        movies.clear();
    }

    @Override
    public int size() {
        return movies.size();
    }

    @Override
    public LocalDateTime getInitializationDate() {
        // Uses the date of the first movie added to the collection 
        return movies.isEmpty() ? LocalDateTime.now() : movies.get(0).getCreationDate();
    }
}
