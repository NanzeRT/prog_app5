package org.itmo.prog.movies.core.views;

import java.util.List;

import javax.annotation.Nullable;

import org.itmo.prog.movies.core.data.Movie;

public interface MovieCollectionView {
    @Nullable Movie get(int id);
    List<Movie> getAll();
    Movie add(Movie.Creator creator);
    Movie add(Movie movie);
    void remove(int id);
    @Nullable Movie update(Movie movie);
    @Nullable Movie update(Movie.Creator creator);
    void clear();
}
