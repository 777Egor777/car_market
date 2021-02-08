package ru.job4j.carmarket.store;

import ru.job4j.carmarket.model.Advertisement;

import java.util.List;

public interface AdvertisementStore extends AutoCloseable {
    <T> T create(T model);
    <T> T update(T model);
    <T> List<T> getAll(Class<T> cl);
    List<Advertisement> getTodayAds();
    List<Advertisement> getAdsByBrand(String brand);
    List<Advertisement> getAdsWithPhoto();
    Advertisement getAdById(int id);
}
