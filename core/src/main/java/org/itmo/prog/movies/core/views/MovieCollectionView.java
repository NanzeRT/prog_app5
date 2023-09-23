package org.itmo.prog.movies.core.views;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Nullable;

import org.itmo.prog.movies.core.data.Movie;

public interface MovieCollectionView {
    public @Nullable Movie get(int id);
    public List<Movie> getAll();
    public Movie add(Movie.Creator creator);
    public Movie add(Movie movie);
    public void remove(int id);
    public @Nullable Movie update(Movie movie);
    public @Nullable Movie update(Movie.Creator creator);
    public void clear();
    public int size();
    public LocalDateTime getInitializationDate();
}
