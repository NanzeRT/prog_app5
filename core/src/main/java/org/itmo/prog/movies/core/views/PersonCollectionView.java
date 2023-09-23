package org.itmo.prog.movies.core.views;

import java.util.List;

import javax.annotation.Nullable;

import org.itmo.prog.movies.core.data.Person;

public interface PersonCollectionView {
    public @Nullable Person get(String id);
    public List<Person> getAll();
    /**
    * @return null if person with such id already exists
    */
    public @Nullable Person add(Person.Creator creator);
    /**
    * @return null if person with such id already exists
    */
    public @Nullable Person add(Person person);
    public void remove(String id);
    public @Nullable Person update(Person.Creator creator);
    public @Nullable Person update(Person person);
    public void clear();
    public boolean contains(String id);
    public int size();
}
