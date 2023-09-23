package org.itmo.prog.movies.core.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Movie implements Comparable<Movie>, Serializable {
    private final Integer id;
    private final String name;
    private final Coordinates coordinates;
    private final LocalDateTime creationDate;
    private final Integer oscarsCount;
    private final MovieGenre genre;
    private final @Nullable MpaaRating mpaaRating;
    private final Person operator;

    public static class Creator {
        private Integer id;
        private String name;
        private Coordinates coordinates;
        private LocalDateTime creationDate;
        private Integer oscarsCount;
        private MovieGenre genre;
        private @Nullable MpaaRating mpaaRating;
        private Person operator;

        public Movie create() {
            return new Movie(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    oscarsCount,
                    genre,
                    mpaaRating,
                    operator);
        }

        public static Creator from(@Nonnull Movie movie) {
            Creator creator = new Creator();
            creator.setId(movie.getId());
            creator.setName(movie.getName());
            creator.setCoordinates(movie.getCoordinates());
            creator.setCreationDate(movie.getCreationDate());
            creator.setOscarsCount(movie.getOscarsCount());
            creator.setGenre(movie.getGenre());
            creator.setMpaaRating(movie.getMpaaRating());
            creator.setOperator(movie.getOperator());
            return creator;
        }

        /**
         * @param id не может быть null, Значение поля должно быть больше 0, Значение этого поля
         *     должно быть уникальным, Значение этого поля должно генерироваться автоматически
         */
        public void setId(@Nonnull Integer id) {
            if (id <= 0) {
                throw new IllegalArgumentException("Значение поля должно быть больше 0.");
            }
            this.id = id;
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
         * @param coordinates не может быть null
         */
        public void setCoordinates(@Nonnull Coordinates coordinates) {
            this.coordinates = coordinates;
        }
        /**
         * @param creationDate не может быть null, Значение этого поля должно генерироваться
         *     автоматически
         */
        public void setCreationDate(@Nonnull java.time.LocalDateTime creationDate) {
            this.creationDate = creationDate;
        }
        /**
         * @param oscarsCount должно быть больше 0, Поле не может быть null
         */
        public void setOscarsCount(@Nonnull Integer oscarsCount) {
            if (oscarsCount <= 0) {
                throw new IllegalArgumentException("Значение поля должно быть больше 0.");
            }
            this.oscarsCount = oscarsCount;
        }
        /**
         * @param genre не может быть null
         */
        public void setGenre(@Nonnull MovieGenre genre) {
            this.genre = genre;
        }
        /**
         * @param mpaaRating может быть null
         */
        public void setMpaaRating(@Nullable MpaaRating mpaaRating) {
            this.mpaaRating = mpaaRating;
        }
        /**
         * @param operator не может быть null
         */
        public void setOperator(@Nonnull Person operator) {
            this.operator = operator;
        }
    }

    /**
     * @param id не может быть null, Значение поля должно быть больше 0, Значение этого поля должно
     *     быть уникальным, Значение этого поля должно генерироваться автоматически
     * @param name не может быть null, Строка не может быть пустой
     * @param coordinates не может быть null
     * @param creationDate не может быть null, Значение этого поля должно генерироваться
     *     автоматически
     * @param oscarsCount должно быть больше 0, Поле не может быть null
     * @param genre не может быть null
     * @param mpaaRating может быть null
     * @param operator не может быть null
     */
    private Movie(
            @Nonnull Integer id,
            @Nonnull String name,
            @Nonnull Coordinates coordinates,
            @Nonnull LocalDateTime creationDate,
            @Nonnull Integer oscarsCount,
            @Nonnull MovieGenre genre,
            @Nullable MpaaRating mpaaRating,
            @Nonnull Person operator) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
    }
    /**
     * @return не может быть null, Значение поля должно быть больше 0, Значение этого поля должно
     *     быть уникальным, Значение этого поля должно генерироваться автоматически
     */
    public @Nonnull Integer getId() {
        return id;
    }
    /**
     * @return не может быть null, Строка не может быть пустой
     */
    public @Nonnull String getName() {
        return name;
    }
    /**
     * @return не может быть null
     */
    public @Nonnull Coordinates getCoordinates() {
        return coordinates;
    }
    /**
     * @return не может быть null, Значение этого поля должно генерироваться автоматически
     */
    public @Nonnull java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }
    /**
     * @return должно быть больше 0, Поле не может быть null
     */
    public @Nonnull Integer getOscarsCount() {
        return oscarsCount;
    }
    /**
     * @return не может быть null
     */
    public @Nullable MovieGenre getGenre() {
        return genre;
    }
    /**
     * @return может быть null
     */
    public @Nonnull MpaaRating getMpaaRating() {
        return mpaaRating;
    }
    /**
     * @return не может быть null
     */
    public @Nonnull Person getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", coordinates="
                + coordinates
                + ", creationDate="
                + creationDate
                + ", oscarsCount="
                + oscarsCount
                + ", genre="
                + genre
                + ", mpaaRating="
                + mpaaRating
                + ", operator="
                + operator
                + '}';
    }

    @Override
    public int compareTo(Movie arg0) {
        return oscarsCount.compareTo(arg0.oscarsCount);
    }
}
