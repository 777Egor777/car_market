package ru.job4j.carmarket.model;

import ru.job4j.carmarket.store.HibernateAdvertisementStore;

import javax.persistence.*;
import java.util.StringJoiner;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 01.02.2021
 */
@Entity
@Table(name = "year")
public class Year {
    @Id
    private int value;

    public Year() {
    }

    public Year(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Year year = (Year) o;

        return value == year.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Year.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
