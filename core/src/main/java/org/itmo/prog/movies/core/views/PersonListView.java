package org.itmo.prog.movies.core.views;

import org.itmo.prog.movies.core.data.Person;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public final class PersonListView implements PersonCollectionView {
    private final List<Person> persons;

    public PersonListView(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public @Nullable Person get(String id) {
        return persons.stream()
                .filter(person -> person.getPassportID().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Person> getAll() {
        return Collections.unmodifiableList(persons);
    }


    @Override
    public @Nullable Person add(Person person) {
        if (contains(person.getPassportID())) {
            return null;
        }
        return add(Person.Creator.from(person));
    }

    @Override
    public @Nullable Person add(Person.Creator creator) {
        return add(creator.create());
    }

    @Override
    public void remove(String id) {
        persons.removeIf(person -> person.getPassportID().equals(id));
    }

    @Override
    public @Nullable Person update(Person person) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getPassportID().equals(person.getPassportID())) {
                persons.set(i, person);
                return person;
            }
        }
        return null;
    }

    @Override
    public @Nullable Person update(Person.Creator creator) {
        return update(creator.create());
    }

    @Override
    public void clear() {
        persons.clear();
    }

    @Override
    public boolean contains(String id) {
        return persons.stream().anyMatch(person -> person.getPassportID().equals(id));
    }
}
