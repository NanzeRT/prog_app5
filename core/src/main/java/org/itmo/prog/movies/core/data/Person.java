package org.itmo.prog.movies.core.data;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Person implements Serializable {
    private String name;
    private @Nullable String passportID;
    private Country nationality;

    public static class Creator {
        private String name;
        private @Nullable String passportID;
        private Country nationality;

        public Person create() {
            return new Person(nationality, name, passportID);
        }

        public static Creator from(@Nonnull Person person) {
            Creator creator = new Creator();
            creator.setName(person.getName());
            creator.setPassportID(person.getPassportID());
            creator.setNationality(person.getNationality());
            return creator;
        }
        
        /**
         * @param name не может быть null, Строка не может быть пустой
         */ 
        public void setName(@Nonnull String name) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Строка не может быть пустой.");
            }
            this.name = name;
        }
        /**
         * @param passportID должно быть уникальным, Длина строки должна быть не меньше 10, Поле может
         *     быть null
         */ 
        public void setPassportID(@Nullable String passportID) {
            if (passportID != null && passportID.length() < 10) {
                throw new IllegalArgumentException("Длина строки должна быть не меньше 10.");
            }
            this.passportID = passportID;
        }
        /**
         * @param nationality не может быть null
         */ 
        public void setNationality(@Nonnull Country nationality) {
            this.nationality = nationality;
        }
    }

    /**
     * @param name не может быть null, Строка не может быть пустой
     * @param passportID должно быть уникальным, Длина строки должна быть не меньше 10, Поле может
     *     быть null
     * @param nationality не может быть null
     */
    private Person(@Nonnull Country nationality, @Nonnull String name, @Nullable String passportID) {
        this.name = name;
        this.passportID = passportID;
        this.nationality = nationality;
    }
    /**
     * @return не может быть null, Строка не может быть пустой
     */
    public @Nonnull String getName() {
        return name;
    }
    /**
     * @return должно быть уникальным, Длина строки должна быть не меньше 10, Поле может быть null
     */
    public @Nullable String getPassportID() {
        return passportID;
    }
    /**
     * @return не может быть null
     */
    public @Nonnull Country getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name="
                + name
                + ", passportID="
                + passportID
                + ", nationality="
                + nationality
                + '}';
    }

    // comparison
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return passportID.equals(other.passportID) && name.equals(other.name) && nationality.equals(other.nationality);
    }
}
