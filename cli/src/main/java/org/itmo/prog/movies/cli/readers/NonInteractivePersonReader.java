package org.itmo.prog.movies.cli.readers;

import java.io.BufferedReader;
import java.io.IOException;

import org.itmo.prog.movies.core.data.Country;
import org.itmo.prog.movies.core.data.Person;
import org.itmo.prog.movies.core.views.PersonCollectionView;

public class NonInteractivePersonReader implements PersonReader {
    private final BufferedReader reader;
    private final PersonCollectionView collectionView;

    public NonInteractivePersonReader(
            BufferedReader reader, PersonCollectionView collectionView) {
        this.reader = reader;
        this.collectionView = collectionView;
    }

    @Override
    public Person create() throws IOException {
        NonInteractiveUtils utils = new NonInteractiveUtils(reader);
        Person.Creator creator = new Person.Creator();
        creator.setPassportID(utils.readNullable(s -> s));
        return createKnownId(creator);
    }

    @Override
    public Person getOrInsert() throws IOException {
        NonInteractiveUtils utils = new NonInteractiveUtils(reader);
        String id = utils.readNullable(s -> s);
        Person person = collectionView.get(id);
        if (person != null) {
            return person;
        }
        Person.Creator creator = new Person.Creator();
        try {
            creator.setPassportID(id);
        } catch (Exception e) {
            throw new IOException("Invalid input: " + e.getMessage());
        }
        return createKnownId(creator);
    }

    private Person createKnownId(Person.Creator creator) throws IOException {
        NonInteractiveUtils utils = new NonInteractiveUtils(reader);
        creator.setName(utils.readNonNull(s -> s));
        creator.setNationality(utils.readNonNull(Country::valueOf));
        return collectionView.add(creator.create());
    }
}

