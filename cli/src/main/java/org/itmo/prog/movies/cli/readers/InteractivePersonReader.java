package org.itmo.prog.movies.cli.readers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

import org.itmo.prog.movies.core.data.Country;
import org.itmo.prog.movies.core.data.Person;
import org.itmo.prog.movies.core.views.PersonCollectionView;

public class InteractivePersonReader implements PersonReader {
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final PersonCollectionView collectionView;

    public InteractivePersonReader(
            BufferedReader reader, BufferedWriter writer, PersonCollectionView collectionView) {
        this.reader = reader;
        this.writer = writer;
        this.collectionView = collectionView;
    }

    @Override
    public Person create() throws IOException {
        InteractiveUtils utils = new InteractiveUtils(reader, writer);
        Person.Creator creator = new Person.Creator();
        utils.setNullable("Enter passport id (String or empty): ", s -> s, creator::setPassportID);
        return createKnownId(creator);
    }

    @Override
    public Person getOrInsert() throws IOException {
        InteractiveUtils utils = new InteractiveUtils(reader, writer);
        while (true) {
            String id = utils.readNullable("Enter person id (String or empty): ", s -> s);
            Person person = collectionView.get(id);
            if (person != null) {
                return person;
            }
            Person.Creator creator = new Person.Creator();
            try {
                creator.setPassportID(id);
            } catch (Exception e) {
                if (writer != null) {
                    writer.write("Invalid input: " + e.getMessage() + "\n");
                    writer.flush();
                }
                continue;
            }
            return createKnownId(creator);
        }
    }

    private Person createKnownId(Person.Creator creator) throws IOException {
        InteractiveUtils utils = new InteractiveUtils(reader, writer);
        utils.setNonNull("Enter person name (String): ", s -> s, creator::setName);
        utils.setNonNull("Possible countries: " + Arrays.asList(Country.values()) + "\nEnter country (Country): ", Country::valueOf, creator::setNationality);
        return collectionView.add(creator.create());
    }
}
