package org.itmo.prog.movies.core.views;

import org.itmo.prog.movies.core.data.Movie;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public final class MovieListView implements MovieCollectionView {
    private final List<Movie> movies;
    private int maxId = 0;

    public MovieListView(List<Movie> movies) {
        this.movies = movies;
        for (Movie movie : movies) maxId = Math.max(maxId, movie.getId());
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
            if (movies.get(i).getId() == movie.getId()) {
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
}
