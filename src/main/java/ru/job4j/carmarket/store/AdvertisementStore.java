package ru.job4j.carmarket.store;

import ru.job4j.carmarket.model.Advertisement;

import java.util.List;

public interface AdvertisementStore {
    <T> T create(T model);
    <T> T update(T model);
    <T> List<T> getAll(Class<T> cl);
    Advertisement getAtById(int id);
}
