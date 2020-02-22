package com.epam.training.entity;

import java.util.Objects;

/**
 * This class discribes Airplane
 */
public class Airplane {
    private int id;
    private String name;
    private int numbersOfSeats;

    public Airplane() {
    }

    public Airplane(String name, int numbersOfSeats) {
        this.id = id;
        this.name = name;
        this.numbersOfSeats = numbersOfSeats;
    }
    public Airplane(int id, String name, int numbersOfSeats) {
        this.id = id;
        this.name = name;
        this.numbersOfSeats = numbersOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbersOfSeats() {
        return numbersOfSeats;
    }

    public void setNumbersOfSeats(int numbersOfSeats) {
        this.numbersOfSeats = numbersOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return id == airplane.id &&
                numbersOfSeats == airplane.numbersOfSeats &&
                Objects.equals(name, airplane.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numbersOfSeats);
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numbersOfSeats=" + numbersOfSeats +
                '}';
    }

}
