package ru.job4j.carmarket.model;

import ru.job4j.carmarket.store.HibernateAdvertisementStore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 01.02.2021
 */
@Entity
@Table(name = "body")
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public BodyType() {
    }

    public BodyType(int id) {
        this.id = id;
    }

    public BodyType(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BodyType bodyType = (BodyType) o;

        if (id != bodyType.id) {
            return false;
        }
        return Objects.equals(name, bodyType.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BodyType.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }

    public static void main(String[] args) {
        List<String> list = List.of(
                "Седан",
                "Хэтчбек",
                "Универсал",
                "Кабриолет",
                "Купе",
                "Внедорожник"
        );
        for (String type: list) {
            HibernateAdvertisementStore.instOf().create(
                    new BodyType(type)
            );
        }
    }
}
