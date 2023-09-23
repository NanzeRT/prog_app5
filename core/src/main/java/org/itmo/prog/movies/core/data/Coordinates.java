package org.itmo.prog.movies.core.data;

import java.io.Serializable;

import javax.annotation.Nonnull;

public final class Coordinates implements Serializable {
    private double x;
    private Float y;

    public static class Creator {
        private double x;
        private Float y;

        public Coordinates create() {
            return new Coordinates(x, y);
        }

        public static Creator from(@Nonnull Coordinates coordinates) {
            Creator creator = new Creator();
            creator.setX(coordinates.getX());
            creator.setY(coordinates.getY());
            return creator;
        }

        /**
         * @param x должно быть больше -746
         */
        public void setX(double x) {
            if (x <= -746) {
                throw new IllegalArgumentException("Должно быть больше -746.");
            }
            this.x = x;
        }

        /**
         * @param y не может быть null
         */
        public void setY(@Nonnull Float y) {
            this.y = y;
        }
    }

    /**
     * @param x должно быть больше -746
     * @param y не может быть null
     */
    private Coordinates(double x, @Nonnull Float y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @return должно быть больше -746
     */
    public double getX() {
        return x;
    }
    /**
     * @return не может быть null
     */
    public @Nonnull Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
