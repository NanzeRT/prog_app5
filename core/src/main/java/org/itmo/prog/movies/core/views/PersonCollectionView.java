package org.itmo.prog.movies.core.views;

import java.util.List;

import javax.annotation.Nullable;

import org.itmo.prog.movies.core.data.Person;

public interface PersonCollectionView {
    @Nullable Person get(String id);
    List<Person> getAll();
    /**
     * @return null if person with such id already exists
     */
    @Nullable Person add(Person.Creator creator);
    /**
     * @return null if person with such id already exists
     */
    @Nullable Person add(Person person);
    void remove(String id);
    @Nullable Person update(Person.Creator creator);
    @Nullable Person update(Person person);
    void clear();
    boolean contains(String id);
}
